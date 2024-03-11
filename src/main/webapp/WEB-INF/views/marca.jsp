<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Marca</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="itemHeader">
                <h3>Configuração - <small>Marca</small></h3>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <form:form class="bootstrap-form-with-validation" id="perfilForm" modelAttribute="tblMarca" action="${pageContext.request.contextPath}/marca/salvar" method="POST">
            <form:hidden path="id" id="field_Id"/>
            <div class="form-group mb-3">
                <form:label path="descricaoMarca" class="form-label" for="text-input">Nome:</form:label>
                <form:input path="descricaoMarca" class="form-control" type="text" id="field_Name" />
            </div>
            <div class="form-group mb-3">
                <form:label path="categoria" class="form-label">Categoria:</form:label>
                <form:select path="categoria" class="form-control" id="field_CategoriaId">
                    <form:option value="" label=" Selecione "/>
                    <form:options items="${listaCategorias}" itemValue="id" itemLabel="nome"/>
                </form:select>
            </div>
            <div class="form-group mb-3">
                <div class="form-check">
                    <form:checkbox path="ativo" id="field_Active"/>
                    <form:label path="ativo" class="form-check-label" for="field_Active">Ativo</form:label>
                </div>
            </div>
            <div class="form-group mb-3">
                <input type="submit" class="btn btn-primary" id="btnSalvar" value="Salvar" />
            </div>
            </form:form>
        </div>
    </div>
</div>

<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th>Ações</th>
            <th>Id</th>
            <th>Nome</th>
            <th>Categoria</th>
            <th>Ativo</th>
            <th>Data Alteração</th>
        </tr>
        </thead>
        <c:forEach var="marca" items="${listaMarcas}">
            <tbody>
            <tr>
                <td>
                    <a href="#" class="btn-visualizar" onclick="visualizarMarca('${marca.id}', '${marca.categoria.id}', '${marca.descricaoMarca}', '${marca.ativo}'); return false;" title="Visualizar">
                    </a>
                </td>
                <td><c:out value="${marca.id}" /></td>
                <td><c:out value="${marca.descricaoMarca}" /></td>
                <td><c:out value="${marca.categoria.id}" /></td>
                <td><c:out value="${marca.ativo ? 'Sim' : 'Não'}" /></td>
                <td><c:out value="${marca.dataAlteracao}" /></td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>

</body>
</html>
