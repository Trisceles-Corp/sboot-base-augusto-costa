<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Características</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div class="pgHeader">
    <p>Caracteristicas</p>
</div>
<div class="dataFields">
    <form:form id="tipoForm" modelAttribute="tblCaracteristica" action="${pageContext.request.contextPath}/caracteristica/salvar" method="POST">
        <form:hidden path="id" id="field_Id"/>
        <table>
            <tr>
                <td><form:label path="descricaoCaracteristica">Descrição:</form:label></td>
                <td><form:input path="descricaoCaracteristica" id="field_Descricao"/></td>
            </tr>
            <tr>
                <td><form:checkbox path="ativo" id="field_Active" label="Ativo" /></td>
            </tr>
        </table>
        <div>
            <input class="btn btn-primary" type="submit" value="Salvar" />
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/caracteristica/novo">Novo</a>
        </div>
    </form:form>
</div>
<div class="dataGrid">
    <table>
        <tr>
            <th>Ações</th>
            <th>Id</th>
            <th>Descrição</th>
            <th>Ativo</th>
            <th>Data de Criação</th>
        </tr>
        <c:forEach var="caracteristica" items="${listaCaracteristica}">
            <tr>
                <td>
                    <a href="#" onclick="visualizarCaracteristica('${caracteristica.id}', '${caracteristica.descricaoCaracteristica}', '${caracteristica.ativo}'); return false;" title="Visualizar">
                        <img src="${pageContext.request.contextPath}/img/view.png" alt="Visualizar" />
                    </a>
                    <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
                </td>
                <td><c:out value="${caracteristica.id}" /></td>
                <td><c:out value="${caracteristica.descricaoCaracteristica}" /></td>
                <td><c:out value="${caracteristica.ativo ? 'Sim' : 'Não'}" /></td>
                <td><c:out value="${caracteristica.dataCriacao}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
