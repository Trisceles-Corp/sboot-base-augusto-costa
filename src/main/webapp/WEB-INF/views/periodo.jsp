<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Periodos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <script type="text/javascript">
        function visualizarPeriodo(periodoId, nome, ativo) {
            document.querySelector("input[name='tblPeriodo.id']").value = periodoId;
            document.querySelector("textarea[name='tblPeriodo.nome']").value = nome;
            document.querySelector("input[name='tblPeriodo.ativo']").checked = ativo === 'true';
        }
    </script>
</head>
<body>

<form:form id="tipoForm" modelAttribute="tblPeriodo" action="${pageContext.request.contextPath}/periodo/salvar" method="POST">
    <form:hidden path="id" />
    <table>
        <tr>
            <td><form:label path="nome">Nome:</form:label></td>
            <td><form:input path="nome" /></td>
            <td><form:checkbox path="ativo" label="Ativo" /></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
        <a href="${pageContext.request.contextPath}/periodo/novo">Novo</a>
    </div>
</form:form>

<br/>

<table>
    <tr>
        <th>Ações</th>
        <th>Id</th>
        <th>Nome</th>
        <th>Ativo</th>
        <th>Data de Criação</th>
        <th>Criado Por</th>
    </tr>
    <c:forEach var="periodo" items="${listaPeriodos}">
        <tr>
            <td>
                <a href="#" onclick="visualizarPeriodo('${periodo.id}', '${periodo.nome}', '${periodo.ativo}'); return false;" title="Visualizar">
                    <img src="${pageContext.request.contextPath}/img/view.png" alt="Visualizar" />
                </a>
                <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
            </td>
            <td><c:out value="${periodo.id}" /></td>
            <td><c:out value="${periodo.nome}" /></td>
            <td><c:out value="${periodo.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${periodo.dataCriacao}" /></td>
            <td><c:out value="${periodo.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
