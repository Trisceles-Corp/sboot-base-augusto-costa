<%--
  Created by IntelliJ IDEA.
  User: Alexander Andrade
  Date: 11/04/2024
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Augusto Costa</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>

</head>
<body>
<div>
    <div class="itemHeader">
        <h4>Perfil Usuários</h4>
    </div>
    <div class="row" id="perfil-botao-cadastro">
        <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2" id="btn-cadastrar" onclick="toggleFormCadastro()">Cadastrar</button>
    </div>

    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="tblPerfil" action="${pageContext.request.contextPath}/perfil/salvar" method="POST">
        <form:hidden path="id" id="field_Id"/>
        <div class="row">
            <div class="form-group col-md-5">
                <form:label path="nome" class="form-label" for="text-input">Nome:</form:label>
                <form:input path="nome" class="form-control" type="text" id="field_Nome" maxlength="100" required="required" />
            </div>
            <div class="form-group col-md-3">
                <form:label path="tipoPerfil" class="form-label">Tipo de Perfil:</form:label>
                <form:select path="tipoPerfil" class="form-control" id="field_TipoPerfilId">
                    <form:option value="" label=" Selecione "/>
                    <form:options items="${listaTiposPerfil}" itemValue="id" itemLabel="descricao"/>
                </form:select>
            </div>
        </div>
        <div class="mt-2">
            <button type="submit" class="btn btn-primary">Salvar</button>
            <button type="button" class="btn btn-danger m-1" id="cancelar-cadastro" onclick="toggleCloseCadastro()">Cancelar</button>
        </div>
    </form:form>
</div>

<div>
    <table id="tabelaDados" class="table table-bordered table-hover table-responsive my-3">
        <thead class="table-dark">
        <tr class="gridHeader">
            <th scope="col" class="th-editar">Ações</th>
            <th scope="col">Nome</th>
            <th scope="col">Tipo Perfil</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="perfil" items="${listaPerfil}">
            <tr>
                <td class="cel-img-tabela-clientes">
                    <form action="${pageContext.request.contextPath}/perfil/delete/${perfil.id}" method="POST">
                        <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarPerfil('${perfil.id}', '${perfil.tipoPerfil.id}', '${perfil.nome}'); return false;" title="Editar">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/perfil/delete/${perfil.id}')">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                        </a>
                    </form>
                </td>
                <td><c:out value="${perfil.nome}" /></td>
                <td><c:out value="${perfil.tipoPerfil.descricao}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
