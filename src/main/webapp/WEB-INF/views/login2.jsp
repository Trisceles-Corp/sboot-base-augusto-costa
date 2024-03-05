<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>
    <c:if test="${not empty loginError}">
        <div class="alert alert-danger" role="alert">
                ${loginError}
        </div>
    </c:if>

    <section class="login-dark">
        <form:form method="post" modelAttribute="tblUsuario" action="${pageContext.request.contextPath}/login2/login" style="margin: 0px;">
            <h2 class="visually-hidden">Login Form</h2>
            <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
            <div class="mb-3"><input class="form-control" type="email" id="email" name="email" placeholder="Email" required></div>
            <div class="mb-3"><input class="form-control" type="password" id="senha" name="senha" placeholder="Senha" required></div>
            <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Log In</button></div>
            <a class="forgot" href="#">Forgot your email or password?</a>
        </form:form>
    </section>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>