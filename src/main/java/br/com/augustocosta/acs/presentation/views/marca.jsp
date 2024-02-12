<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Marcas</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/css/form-styles.css'/>" />
    <script type="text/javascript">
        function visualizarMarca(marcaId, descricao, ativo) {
            document.querySelector("input[name='tblMarca.id']").value = marcaId;
            document.querySelector("textarea[name='tblMarca.descricaoMarca']").value = descricao;
            document.querySelector("input[name='tblMarca.ativo']").checked = ativo === 'Sim';
        }
    </script>
</head>
<body>

<form:form id="tipoForm" modelAttribute="tblMarca" action="${pageContext.request.contextPath}/marca/salvar" method="POST">
    <form:hidden path="id" />
    <table>
        <tr>
            <td><form:label path="descricaoMarca">Descrição:</form:label></td>
            <td><form:input path="descricaoMarca" /></td>
            <td><form:checkbox path="ativo" label="Ativo" /></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
        <a href="${pageContext.request.contextPath}/marca/novo">Novo</a>
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
    <c:forEach var="marca" items="${listaMarcas}">
        <tr>
            <td>
                <a href="#" onclick="visualizarMarca('${marca.id}', '${marca.descricaoMarca}', '${marca.ativo}'); return false;" title="Visualizar">
                    <img src="<c:url value='/static/images/view.png'/>" alt="Visualizar" />
                </a>
                <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
            </td>
            <td><c:out value="${marca.id}" /></td>
            <td><c:out value="${marca.descricaoMarca}" /></td>
            <td><c:out value="${marca.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${marca.dataCriacao}" /></td>
            <td><c:out value="${marca.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
