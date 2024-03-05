<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Categoria</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div class="pgHeader">
    <p>Categorias</p>
</div>
<form:form id="tipoForm" modelAttribute="tblCategoria" action="${pageContext.request.contextPath}/categoria/salvar" method="POST">
    <form:hidden path="id" id="field_Id" />
    <table>
        <tr>
            <td><form:label path="nome">Nome:</form:label></td>
            <td><form:input path="nome" id="field_Name"/></td>
        </tr>
        <tr>
            <td><form:checkbox path="ativo" id="field_Active" label="Ativo" /></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
        <a href="${pageContext.request.contextPath}/categoria/novo">Novo</a>
    </div>
</form:form>

<br/>

<table>
    <tr>
        <th>Ações</th>
        <th>ID</th>
        <th>Nome</th>
        <th>Ativo</th>
        <th>Data de Criação</th>
        <th>Criado Por</th>
    </tr>
    <c:forEach var="categoria" items="${listaCategorias}">
        <tr>
            <td>
                <a href="#" onclick="visualizarCategoria('${categoria.id}', '${categoria.nome}', '${categoria.ativo}'); return false;" title="Visualizar">
                    <img src="${pageContext.request.contextPath}/img/view.png" alt="Visualizar" />
                </a>
                <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
            </td>
            <td><c:out value="${categoria.id}" /></td>
            <td><c:out value="${categoria.nome}" /></td>
            <td><c:out value="${categoria.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${categoria.dataCriacao}" /></td>
            <td><c:out value="${categoria.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
