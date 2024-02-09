<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Local Estoque</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/css/form-styles.css'/>" />
    <script type="text/javascript">
        function visualizarLocalEstoque(localEstoqueId, descricao, ativo) {
            document.querySelector("input[name='tblLocalEstoque.id']").value = localEstoqueId;
            document.querySelector("textarea[name='tblLocalEstoque.descricaoLocal']").value = descricao;
            document.querySelector("input[name='tblLocalEstoque.ativo']").checked = ativo === 'Sim';
        }
    </script>
</head>
<body>

<form:form id="tipoForm" modelAttribute="tblLocalEstoque" action="${pageContext.request.contextPath}/localestoque/salvar" method="POST">
    <form:hidden path="id" />
    <table>
        <tr>
            <td><form:label path="descricaoLocal">Descrição:</form:label></td>
            <td><form:input path="descricaoLocal" /></td>
            <td><form:checkbox path="ativo" label="Ativo" /></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
        <a href="${pageContext.request.contextPath}/localestoque/novo">Novo</a>
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
                <a href="#" onclick="visualizarLocalEstoque('${localestoque.id}', '${localestoque.descricaoLocal}', '${localestoque.ativo}'); return false;" title="Visualizar">
                    <img src="<c:url value='/static/images/view.png'/>" alt="Visualizar" />
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
