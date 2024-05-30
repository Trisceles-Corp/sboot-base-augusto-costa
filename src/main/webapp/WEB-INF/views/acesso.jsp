<%--
  Created by IntelliJ IDEA.
  User: Alexander Andrade
  Date: 12/04/2024
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <meta name="google-signin-client_id" content="steady-thunder-423317-k2.apps.googleusercontent.com">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/ionicons.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login-Form-Dark.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mloureiro1973-login.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mloureiro1973-login-1.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />

        <script src="https://apis.google.com/js/platform.js" async defer></script>
    </head>
    <body>
        <c:if test="${not empty loginError}">
            <div class="alert alert-danger" role="alert">
                    ${loginError}
            </div>
        </c:if>

        <div class="login-card">
            <div>
                <h2 class="text-center">Augusto Costa Spa</h2>
            </div>
            <img class="img-fluid profile-img-card" src="${pageContext.request.contextPath}/img/avatars/avatar_2x.png">
            <p class="profile-name-card"> </p>
            <form:form class="form-signin" method="post" modelAttribute="tblUsuario" action="${pageContext.request.contextPath}/acesso/verify" >
                <div class="mb-4">
                    <input class="form-control" type="email" id="email" name="email" placeholder="Email" required="required">
                </div>
                <div class="mb-4">
                    <input class="form-control" type="password" id="senha" name="senha" placeholder="Senha" required="required">
                </div>
                <div class="mb-3">
                    <button class="btn btn-primary btn-lg d-block btn-signin w-100" id="btnLogin" name="login" type="submit">Log In</button>
                </div>
                <div class="g-signin2 mb-3" data-onsuccess="onSignIn"></div>
                <div class="mb-3" style="display: none">
                    <a class="forgot-password" href="#">Recuperar senha?</a>
                </div>
                <div class="mb-3" style="display: none">
                    <a class="forgot" href="${pageContext.request.contextPath}/caminhoParaCadastro">Novo usuário? Cadastre-se!</a>
                </div>
            </form:form>
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>