<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Perfil</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div class="pgHeader">
    <p>Perfil Usuário</p>
</div>

<div>
    <form:form id="perfilForm" modelAttribute="tblPerfil" action="${pageContext.request.contextPath}/perfil/salvar" method="POST">
        <form:hidden path="id" id="field_Id"/>
        <table>
            <tr>
                <td><form:label path="nome">Nome:</form:label></td>
                <td><form:input path="nome" id="field_Name"/></td>
            </tr>
            <tr>
                <td><form:label path="tipoPerfil">Tipo de Perfil:</form:label></td>
                <td>
                    <form:select path="tipoPerfil" id="field_TipoPerfilId">
                        <form:option value="" label="-- Selecione --"/>
                        <form:options items="${listaTiposPerfil}" itemValue="id" itemLabel="descricao"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><form:checkbox path="ativo" label="Ativo" id="field_Active"/></td>
            </tr>
        </table>
        <div class="button-bar">
            <input type="submit" value="Salvar" />
            <a href="${pageContext.request.contextPath}/perfil/novo">Novo</a>
        </div>
    </form:form>
</div>

<div>
    <table>
        <tr>
            <th>Ações</th>
            <th>Id</th>
            <th>Nome</th>
            <th>Tipo Perfil</th>
            <th>Ativo</th>
            <th>Data de Criação</th>
        </tr>
        <c:forEach var="perfil" items="${listaPerfil}">
            <tr>
                <td>
                    <a href="#" onclick="visualizarPerfil('${perfil.id}', '${perfil.tipoPerfil.id}', '${perfil.nome}', '${perfil.ativo}'); return false;" title="Visualizar">
                        <img src="${pageContext.request.contextPath}/img/view.png" alt="Visualizar" />
                    </a>
                    <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
                </td>
                <td><c:out value="${perfil.id}" /></td>
                <td><c:out value="${perfil.nome}" /></td>
                <td><c:out value="${perfil.tipoPerfil.id}" /></td>
                <td><c:out value="${perfil.ativo ? 'Sim' : 'Não'}" /></td>
                <td><c:out value="${perfil.dataCriacao}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
