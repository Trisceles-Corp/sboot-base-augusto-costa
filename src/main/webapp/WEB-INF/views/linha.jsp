<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Linha</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <script type="text/javascript">
        function visualizarLinha(id, name, active) {
            document.getElementById("field_Id").value = id;
            document.getElementById("field_Name").value = name;
            document.getElementById("field_Active").checked = active === 'true';
        }
    </script>
</head>
<body>

<form:form id="tipoForm" modelAttribute="tblLinha" action="${pageContext.request.contextPath}/linha/salvar" method="POST">
    <form:hidden path="id" id="field_Id"/>
    <table>
        <tr>
            <td><form:label path="descricaoLinha">Descrição:</form:label></td>
            <td><form:input path="descricaoLinha" id="field_Name"/></td>
            <td><form:checkbox path="ativo" label="Ativo" id="field_Active"/></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
        <a href="${pageContext.request.contextPath}/linha/novo">Novo</a>
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
    <c:forEach var="linha" items="${listaLinhas}">
        <tr>
            <td>
                <a href="#" onclick="visualizarLinha('${linha.id}', '${linha.descricaoLinha}', '${linha.ativo}'); return false;" title="Visualizar">
                    <img src="${pageContext.request.contextPath}/img/view.png" alt="Visualizar" />
                </a>
                <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
            </td>
            <td><c:out value="${linha.id}" /></td>
            <td><c:out value="${linha.descricaoLinha}" /></td>
            <td><c:out value="${linha.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${linha.dataCriacao}" /></td>
            <td><c:out value="${linha.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
