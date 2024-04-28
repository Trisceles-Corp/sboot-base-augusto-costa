<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Permissões</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div class="pgHeader">
    <p>Permissões</p>
</div>

<form:form id="tipoForm" modelAttribute="tblPermissoes" action="${pageContext.request.contextPath}/permissoes/salvar" method="POST">
    <form:hidden path="id" id="field_Id"/>
    <div class="row">
        <div class="form-group col-md-5">
            <form:label path="nome" class="form-label" for="field_Name">Nome:</form:label>
            <form:input path="nome" class="form-control" id="field_Name"/>
        </div>
        <div class="form-group col-md-5">
            <form:label path="descricao" class="form-label" for="field_Description">Descrição:</form:label>
            <form:input path="descricao" class="form-control" id="field_Description"/>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-1">
            <form:label path="ativo" class="form-label" for="field_Active">Ativo:</form:label>
            <form:checkbox path="ativo" class="form-control" id="field_Active"/>
        </div>
    </div>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
    </div>
</form:form>

<br/>

<table>
    <tr class="gridHeader">
        <th>Ações</th>
        <th>Id</th>
        <th>Nome</th>
        <th>Descrição</th>
        <th>Ativo</th>
        <th>Data de Criação</th>
        <th>Criado Por</th>
    </tr>
    <c:forEach var="permissoes" items="${listaPermissoes}">
        <tr>
            <td>
                <a href="#" class="btn-visualizar" onclick="visualizarPermissoes('${permissoes.id}', '${permissoes.nome}', '${permissoes.descricao}', '${permissoes.ativo}'); return false;" title="Visualizar">
                </a>
            </td>
            <td><c:out value="${permissoes.id}" /></td>
            <td><c:out value="${permissoes.nome}" /></td>
            <td><c:out value="${permissoes.descricao}" /></td>
            <td><c:out value="${permissoes.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${permissoes.dataCriacao}" /></td>
            <td><c:out value="${permissoes.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
