<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>404 Error</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/404-NOT-FOUND-animated.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>

<body>
<main class="page contact-page">
    <section class="portfolio-block contact" style="background: #080808;color: rgb(255,255,255);">
        <div class="container">
            <!--
VIEW IN FULL SCREEN MODE
FULL SCREEN MODE: http://salehriaz.com/404Page/404.html

DRIBBBLE: https://dribbble.com/shots/4330167-404-Page-Lost-In-Space
-->
            <body class="bg-purple">
                <div class="stars">
                    <div class="central-body">
                        <h1>${errorTitle}</h1>
                        <h2>Error Code: ${errorCode}</h2>
                        <h2 style="text-align: center">${errorMessage}</h2>
                        <a href="${pageContext.request.contextPath}/main" class="btn-go-home">Retornar</a>
                    </div>
                    <div class="objects">
                        <img class="object_rocket" src="${pageContext.request.contextPath}/img/muppers/rocket.svg" width="40px">
                        <div class="earth-moon">
                            <img class="object_earth" src="${pageContext.request.contextPath}/img/muppers/earth.svg" width="100px">
                            <img class="object_moon" src="${pageContext.request.contextPath}/img/muppers/moon.svg" width="80px">
                        </div>
                        <div class="box_astronaut">
                            <img class="object_astronaut" src="${pageContext.request.contextPath}/img/muppers/astronaut.svg" width="140px">
                        </div>
                    </div>
                    <div class="glowing_stars">
                        <div class="star"></div>
                        <div class="star"></div>
                        <div class="star"></div>
                        <div class="star"></div>
                        <div class="star"></div>
                    </div>
                </div>
            </body>
        </div>
    </section>
</main>
</body>

</html>