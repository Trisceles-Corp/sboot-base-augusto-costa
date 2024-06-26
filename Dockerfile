# syntax=docker/dockerfile:1

################################################################################

# Create a stage for resolving and downloading dependencies.
FROM maven:3.8.4-openjdk-17 as deps

WORKDIR /build

# Copy the mvnw wrapper with executable permissions.
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw

# Download dependencies as a separate step to take advantage of Docker's caching.
# Leverage a cache mount to /root/.m2 so that subsequent builds don't have to
# re-download packages.
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline -DskipTests -P gcp

################################################################################

# Create a stage for building the application based on the stage with downloaded dependencies.
FROM deps as package

WORKDIR /build

COPY ./src src/
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 \
    mvn package -DskipTests -P gcp && \
    mv target/acs-0.0.1-SNAPSHOT.war target/app.war

################################################################################

# Create a stage for verifying the contents of the WAR file using a JDK image.
FROM openjdk:17-jdk-slim as verify

COPY --from=package /build/target/app.war /app.war

# Verify the contents of the WAR file
RUN jar tf /app.war | grep "org/springframework/boot/loader/launch/WarLauncher.class"

################################################################################

# Create a new stage for running the application that contains the minimal
# runtime dependencies for the application.
FROM eclipse-temurin:17-jre-jammy AS final

# Create a non-privileged user that the app will run under.
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

# Copy the executable from the "verify" stage.
COPY --from=verify /app.war /app.war

# Copy the keystore file
COPY src/main/resources/acsapp.keystore.p12 /app/resources/acsapp.keystore.p12

EXPOSE 8443

ENTRYPOINT ["java", "-jar", "/app.war"]