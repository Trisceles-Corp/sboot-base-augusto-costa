<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Tipos Movimentação</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/src/main/resources/static/css/form-styles.css'/>" />
    <script type="text/javascript">
        function visualizarDiasSemana(tipoMovimentacaoId, descricao, ativo) {
            document.querySelector("input[name='tblTipoMovimentacao.id']").value = tipoMovimentacaoId;
            document.querySelector("textarea[name='tblTipoMovimentacao.descricaoMovimentacao']").value = descricao;
            document.querySelector("input[name='tblTipoMovimentacao.ativo']").checked = ativo === 'Sim';
        }
    </script>
</head>
<body>

<form:form id="tipoForm" modelAttribute="tblTipoMovimentacao" action="${pageContext.request.contextPath}/tipomovimentacao/salvar" method="POST">
    <form:hidden path="id" />
    <table>
        <tr>
            <td><form:label path="descricaoMovimentacao">Descrição:</form:label></td>
            <td><form:input path="descricaoMovimentacao" /></td>
            <td><form:checkbox path="ativo" label="Ativo" /></td>
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
                    <img src="<c:url value='/src/main/resources/static/images/view.png'/>" alt="Visualizar" />
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
