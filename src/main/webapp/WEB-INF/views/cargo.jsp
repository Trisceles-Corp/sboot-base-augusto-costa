<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Cargos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div class="pgHeader">
    <p>Cargos</p>
</div>

<form:form id="cargoForm" modelAttribute="tblCargo" action="${pageContext.request.contextPath}/cargo/salvar" method="POST">
    <form:hidden path="id" id="field_Id"/>
    <table>
        <tr>
            <td><form:label path="nome">Nome:</form:label></td>
            <td><form:input path="nome" id="field_Name"/></td>
        </tr>
        <tr>
            <td><form:checkbox path="ativo" label="Ativo" id="field_Active" /></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
        <a href="${pageContext.request.contextPath}/cargo/novo">Novo</a>
    </div>
</form:form>

<br/>

<table>
    <tr>
        <th>Ações</th>
        <th>Id</th>
        <th>Nome</th>
        <th>Ativo</th>
        <th>Data de Criação</th>
        <th>Criado Por</th>
    </tr>
    <c:forEach var="cargo" items="${listaCargos}">
        <tr>
            <td>
                <a href="#" onclick="visualizarCargo('${cargo.id}', '${cargo.nome}', '${cargo.ativo}'); return false;"
                   title="Visualizar">
                    <img src="${pageContext.request.contextPath}/img/view.png" alt="Visualizar" />
                </a>
            </td>
            <td><c:out value="${cargo.id}" /></td>
            <td><c:out value="${cargo.nome}" /></td>
            <td><c:out value="${cargo.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${cargo.dataCriacao}" /></td>
            <td><c:out value="${cargo.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>