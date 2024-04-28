<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Periodos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div class="pgHeader">
    <p>Periodo</p>
</div>

<form:form id="tipoForm" modelAttribute="tblPeriodo" action="${pageContext.request.contextPath}/periodo/salvar" method="POST">
    <form:hidden path="id" id="field_Id"/>
    <div class="row">
        <div class="form-group col-md-5">
            <form:label path="nome" class="form-label" for="field_Name">Nome:</form:label>
            <form:input path="nome" class="form-control" type="text" id="field_Name"/>
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
        <th>Ativo</th>
        <th>Data de Criação</th>
        <th>Criado Por</th>
    </tr>
    <c:forEach var="periodo" items="${listaPeriodos}">
        <tr>
            <td>
                <a href="#" class="btn-visualizar" onclick="visualizarPeriodo('${periodo.id}', '${periodo.nome}', '${periodo.ativo}'); return false;" title="Visualizar">
                </a>
            </td>
            <td><c:out value="${periodo.id}" /></td>
            <td><c:out value="${periodo.nome}" /></td>
            <td><c:out value="${periodo.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${periodo.dataCriacao}" /></td>
            <td><c:out value="${periodo.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
