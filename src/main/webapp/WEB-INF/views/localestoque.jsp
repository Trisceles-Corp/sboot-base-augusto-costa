<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Local Estoque</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div class="pgHeader">
    <p>Linha</p>
</div>

<form:form id="tipoForm" modelAttribute="tblLocalEstoque" action="${pageContext.request.contextPath}/localestoque/salvar" method="POST">
    <form:hidden path="id" id="field_Id"/>
    <table>
        <tr>
            <td><form:label path="descricaoLocal">Descrição:</form:label></td>
            <td><form:input path="descricaoLocal" id="field_Name"/></td>
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
    <tr>
        <th>Ações</th>
        <th>Id</th>
        <th>Descrição</th>
        <th>Ativo</th>
        <th>Data de Criação</th>
        <th>Criado Por</th>
    </tr>
    <c:forEach var="localestoque" items="${listalocais}">
        <tr>
            <td>
                <a href="#" class="btn-visualizar" onclick="visualizarLocalEstoque('${localestoque.id}', '${localestoque.descricaoLocal}', '${localestoque.ativo}'); return false;" title="Visualizar">
                </a>
                <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
            </td>
            <td><c:out value="${localestoque.id}" /></td>
            <td><c:out value="${localestoque.descricaoLocal}" /></td>
            <td><c:out value="${localestoque.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${localestoque.dataCriacao}" /></td>
            <td><c:out value="${localestoque.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
