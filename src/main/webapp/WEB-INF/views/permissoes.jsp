<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Permissões</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <script type="text/javascript">
        function visualizarPermissoes(id, name, description, active) {
            document.getElementById("field_Id").value = id;
            document.getElementById("field_Name").value = name;
            document.getElementById("field_Description").value = description;
            document.getElementById("field_Active").checked = active === 'true';
        }
    </script>
</head>
<body>

<form:form id="tipoForm" modelAttribute="tblPermissoes" action="${pageContext.request.contextPath}/permissoes/salvar" method="POST">
    <form:hidden path="id" id="field_Id"/>
    <table>
        <tr>
            <td><form:label path="nome">Nome:</form:label></td>
            <td><form:input path="nome" id="field_Name"/></td>
            <td><form:label path="descricao">Descrição:</form:label></td>
            <td><form:input path="descricao" id="field_Description"/></td>
        </tr>
        <tr>
            <td><form:checkbox path="ativo" label="Ativo" id="field_Active"/></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
        <a href="${pageContext.request.contextPath}/permissoes/novo">Novo</a>
    </div>
</form:form>

<br/>

<table>
    <tr>
        <th>Ações</th>
        <th>Id</th>
        <th>Nome</th>
        <th>Descrição</th>
        <th>Ativo</th>
        <th>Data de Criação</th>
        <th>Criado Por</th>
    </tr>
    <c:forEach var="permissoes" items="${listaPermissoes}">
        <tr>
            <td>
                <a href="#" onclick="visualizarPermissoes('${permissoes.id}', '${permissoes.nome}', '${permissoes.descricao}', '${permissoes.ativo}'); return false;" title="Visualizar">
                    <img src="${pageContext.request.contextPath}/img/view.png" alt="Visualizar" />
                </a>
                <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
            </td>
            <td><c:out value="${permissoes.id}" /></td>
            <td><c:out value="${permissoes.nome}" /></td>
            <td><c:out value="${permissoes.descricao}" /></td>
            <td><c:out value="${permissoes.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${permissoes.dataCriacao}" /></td>
            <td><c:out value="${permissoes.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
