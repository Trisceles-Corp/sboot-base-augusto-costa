<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dias da Semana</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div class="pgHeader">
    <p>Dias da Semana</p>
</div>

<form:form id="tipoForm" modelAttribute="tblDiasSemana" action="${pageContext.request.contextPath}/diassemana/salvar" method="POST">
    <form:hidden path="id" id="field_Id"/>
    <table>
        <tr>
            <td><form:label path="diasSemana" class="form-label" for="field_Name">Descrição:</form:label></td>
            <td><form:input path="diasSemana" class="form-control" id="field_Name"/></td>
        </tr>
        <tr>
            <td><form:checkbox path="ativo" label="Ativo" id="field_Active"/></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
    </div>
</form:form>

<br/>

<table>
    <tr class="gridHeader">
        <th>Ações</th>
        <th>Id</th>
        <th>Descrição</th>
        <th>Ativo</th>
        <th>Data de Criação</th>
        <th>Criado Por</th>
    </tr>
    <c:forEach var="diassemana" items="${listaDiasSemana}">
        <tr>
            <td>
                <a href="#" class="btn-visualizar" onclick="visualizarDiasSemana('${diassemana.id}', '${diassemana.diasSemana}', '${diassemana.ativo}'); return false;" title="Visualizar">
                </a>
            </td>
            <td><c:out value="${diassemana.id}" /></td>
            <td><c:out value="${diassemana.diasSemana}" /></td>
            <td><c:out value="${diassemana.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${diassemana.dataCriacao}" /></td>
            <td><c:out value="${diassemana.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
