<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dias da Semana</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/src/main/resources/static/css/form-styles.css'/>" />
    <script type="text/javascript">
        function visualizarDiasSemana(diasSemanaId, descricao, ativo) {
            document.querySelector("input[name='tblDiasSemana.id']").value = diasSemanaId;
            document.querySelector("textarea[name='tblDiasSemana.diasSemana']").value = descricao;
            document.querySelector("input[name='tblDiasSemana.ativo']").checked = ativo === 'Sim';
        }
    </script>
</head>
<body>

<form:form id="tipoForm" modelAttribute="tblDiasSemana" action="${pageContext.request.contextPath}/diassemana/salvar" method="POST">
    <form:hidden path="id" />
    <table>
        <tr>
            <td><form:label path="diasSemana">Descrição:</form:label></td>
            <td><form:input path="diasSemana" /></td>
            <td><form:checkbox path="ativo" label="Ativo" /></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
        <a href="${pageContext.request.contextPath}/diassemana/novo">Novo</a>
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
    <c:forEach var="diassemana" items="${listaDiasSemana}">
        <tr>
            <td>
                <a href="#" onclick="visualizarDiasSemana('${diassemana.id}', '${diassemana.diasSemana}', '${diassemana.ativo}'); return false;" title="Visualizar">
                    <img src="<c:url value='/src/main/resources/static/images/view.png'/>" alt="Visualizar" />
                </a>
                <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
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
