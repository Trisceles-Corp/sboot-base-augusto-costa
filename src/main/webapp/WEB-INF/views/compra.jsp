<%--
  Created by IntelliJ IDEA.
  User: Alexander Andrade
  Date: 12/04/2024
  Time: 17:24
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
        <h4>Pedido de Compras</h4>
    </div>
    <div class="row" id="linha-botao-cadastro">
        <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2" id="btn-cadastrar" onclick="toggleFormCadastro()">Cadastrar</button>
    </div>

    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="tblCompra" action="${pageContext.request.contextPath}/compra/salvar" method="POST">
        <form:hidden path="id" id="field_Id"/>
        <div class="row">
            <div class="form-group col-md-4">
                <form:label path="localEstoque" class="form-label">Local Estoque:</form:label>
                <form:select path="localEstoque" class="form-control" id="field_LocalEstoqueId">
                    <form:option value="" label=" Selecione "/>
                    <form:options items="${listarLocalEstoque}" itemValue="id" itemLabel="descricaoLocal"/>
                </form:select>
            </div>
            <div class="form-group col-md-2">
                <form:label path="situacaoCompra" class="form-label">Situação:</form:label>
                <form:select path="situacaoCompra" class="form-control" id="field_SituacaoCompraId">
                    <form:option value="" label=" Selecione "/>
                    <form:options items="${listarSituacao}" itemValue="id" itemLabel="nome"/>
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
            <th scope="col">Local Estoque</th>
            <th scope="col">Situação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="compra" items="${listarCompra}">
            <tr>
                <td class="cel-img-tabela-clientes">
                    <form action="${pageContext.request.contextPath}/compra/delete/${compra.id}" method="POST">
                        <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarSituacaoAgendamento('${compra.id}', '${compra.localEstoque.id}', '${compra.situacaoCompra.id}'); return false;" title="Editar">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/compra/delete/${compra.id}')">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                        </a>
                    </form>
                </td>
                <td><c:out value="${compra.localEstoque.descricaoLocal}" /></td>
                <td><c:out value="${compra.situacaoCompra.nome}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
