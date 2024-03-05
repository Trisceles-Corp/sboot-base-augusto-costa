<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Tipos Movimentação</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div class="pgHeader">
    <p>Tipo movimentação</p>
</div>

<form:form id="tipoForm" modelAttribute="tblTipoMovimentacao" action="${pageContext.request.contextPath}/tipomovimentacao/salvar" method="POST">
    <form:hidden path="id" id="field_Id"/>
    <table>
        <tr>
            <td><form:label path="descricaoMovimentacao">Descrição:</form:label></td>
            <td><form:input path="descricaoMovimentacao" id="field_Name"/></td>
        </tr>
        <tr>
            <td><form:checkbox path="ativo" label="Ativo" id="field_Active"/></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
        <a href="${pageContext.request.contextPath}/tipomovimentacao/novo">Novo</a>
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
    <c:forEach var="tipomovimentacao" items="${listaTipoMovimentacao}">
        <tr>
            <td>
                <a href="#" onclick="visualizarDiasSemana('${tipomovimentacao.id}', '${tipomovimentacao.descricaoMovimentacao}', '${tipomovimentacao.ativo}'); return false;" title="Visualizar">
                    <img src="${pageContext.request.contextPath}/img/view.png" alt="Visualizar" />
                </a>
                <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
            </td>
            <td><c:out value="${tipomovimentacao.id}" /></td>
            <td><c:out value="${tipomovimentacao.descricaoMovimentacao}" /></td>
            <td><c:out value="${tipomovimentacao.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${tipomovimentacao.dataCriacao}" /></td>
            <td><c:out value="${tipomovimentacao.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
