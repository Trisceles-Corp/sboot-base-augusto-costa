<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Características</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <script type="text/javascript">
        function visualizarCaracteristica(caracteristicaId, descricao, ativo) {
            document.querySelector("input[name='tblCaracteristica.id']").value = caracteristicaId;
            document.querySelector("textarea[name='tblCaracteristica.descricaoCaracteristica']").value = descricao;
            document.querySelector("input[name='tblCaracteristica.ativo']").checked = ativo === 'true';
        }
    </script>
</head>
<body>

<form:form id="tipoForm" modelAttribute="tblCaracteristica" action="${pageContext.request.contextPath}/caracteristica/salvar" method="POST">
    <form:hidden path="id" />
    <table>
        <tr>
            <td><form:label path="descricaoCaracteristica">Descrição:</form:label></td>
            <td><form:input path="descricaoCaracteristica" /></td>
            <td><form:checkbox path="ativo" label="Ativo" /></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
        <a href="${pageContext.request.contextPath}/caracteristica/novo">Novo</a>
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
    <c:forEach var="caracteristica" items="${listaCaracteristica}">
        <tr>
            <td>
                <a href="#" onclick="visualizarCaracteristica('${caracteristica.id}', '${caracteristica.descricaoCaracteristica}', '${caracteristica.ativo}'); return false;" title="Visualizar">
                    <img src="${pageContext.request.contextPath}/images/view.png" alt="Visualizar" />
                </a>
                <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
            </td>
            <td><c:out value="${caracteristica.id}" /></td>
            <td><c:out value="${caracteristica.descricaoCaracteristica}" /></td>
            <td><c:out value="${caracteristica.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${caracteristica.dataCriacao}" /></td>
            <td><c:out value="${caracteristica.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
