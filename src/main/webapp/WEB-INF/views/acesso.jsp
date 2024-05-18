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
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/ionicons.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Login-Form-Dark.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mloureiro1973-login-1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
<c:if test="${not empty loginError}">
    <div class="alert alert-danger" role="alert">
            ${loginError}
    </div>
</c:if>

<div class="login-card">
    <div style="padding: 20px">
        <h2 class="text-center">Augusto Costa Spa</h2>
    </div>
    <img class="img-fluid profile-img-card" src="${pageContext.request.contextPath}/img/logos/image(5).png">
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
<%--        <div class="mb-3">--%>
<%--            <a class="forgot-password" href="#">Recuperar senha?</a>--%>
<%--        </div>--%>
        <div class="mb-3">
            <a class="forgot-password" href="${pageContext.request.contextPath}/caminhoParaCadastro">Novo cadastro</a>
        </div>
    </form:form>
</div>
<script src="${pageContext.request.contextPath}/resources/static/js/bootstrap.min.js"></script>
</body>
</html>