<%--
  Created by IntelliJ IDEA.
  User: ck_aa
  Date: 11/03/2024
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Produto</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>

</head>
<body>
<div>
    <div class="row" id="linha-botao-cadastro">
        <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2 " id="btn-cadastrar" onclick="toggleFormCadastro()">Cadastrar Produto</button>
    </div>

    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="tblProduto" action="${pageContext.request.contextPath}/produto/salvar" method="POST" onsubmit="return verificarNomeAntesDeSalvar()">
        <form:hidden path="id" id="field_Id"/>
        <div class="row">
            <div class="form-group col-md-4">
                <form:label path="descricaoProduto" class="form-label" for="text-input">Nome:</form:label>
                <form:input path="descricaoProduto" class="form-control" type="text" id="field_Name" />
            </div>
            <div class="form-group col-md-2">
                <form:label path="codigoInterno" class="form-label" for="text-input">Codigo Interno:</form:label>
                <form:input path="codigoInterno" class="form-control" type="text" id="field_CodigoInterno" />
            </div>
            <div class="form-group col-md-2">
                <form:label path="codigoBarras" class="form-label" for="text-input">Codigo de Barras:</form:label>
                <form:input path="codigoBarras" class="form-control" type="text" id="field_CodigoBarras" />
            </div>
            <div class="form-group col-md-1">
                <form:label path="custo" class="form-label" for="text-input">Custo:</form:label>
                <form:input path="custo" class="form-control" type="text" id="field_Custo" />
            </div>
            <div class="form-group col-md-1">
                <form:label path="valorVenda" class="form-label" for="text-input">Valor Venda:</form:label>
                <form:input path="valorVenda" class="form-control" type="text" id="field_valorVenda" />
            </div>
            <div class="form-group col-md-1">
                <form:label path="comissao" class="form-label" for="text-input">Comissão:</form:label>
                <form:input path="comissao" class="form-control" type="text" id="field_Comissao" />
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-2">
                <form:label path="marca" class="form-label">Marca:</form:label>
                <form:select path="marca" class="form-control" id="field_MarcaId">
                    <form:option value="" label=" Selecione "/>
                    <form:options items="${listaMarcas}" itemValue="id" itemLabel="descricaoMarca"/>
                </form:select>
            </div>
            <div class="form-group col-md-2">
                <form:label path="linha" class="form-label">Linha:</form:label>
                <form:select path="linha" class="form-control" id="field_LinhaId">
                    <form:option value="" label=" Selecione "/>
                    <form:options items="${listaLinhas}" itemValue="id" itemLabel="descricaoLinha"/>
                </form:select>
            </div>
            <div class="form-group col-md-2">
                <form:label path="caracteristica" class="form-label">Característica:</form:label>
                <form:select path="caracteristica" class="form-control" id="field_CaracteristicaId">
                    <form:option value="" label=" Selecione "/>
                    <form:options items="${listaCaracteristicas}" itemValue="id" itemLabel="descricaoCaracteristica"/>
                </form:select>
            </div>
            <div class="form-group col-md-2">
                <form:label path="estoqueMinimo" class="form-label" for="text-input">Estoque Mínimo:</form:label>
                <form:input path="estoqueMinimo" class="form-control" type="text" id="field_EstoqueMinimo" />
            </div>
        </div>
        <div class="mt-2">
            <button type="submit" class="btn btn-primary">Salvar</button>
            <button type="button" class="btn btn-danger m-1" id="cancelar-cadastro" onclick="toggleCloseCadastro()">Cancelar</button>
        </div>
    </form:form>
</div>

<div>
    <table id="tabelaProdutoss" class="table table-bordered table-hover table-responsive my-3">
        <thead class="table-dark">
        <tr class="gridHeader">
            <th scope="col" class="th-editar">Ações</th>
            <th scope="col">Nome</th>
            <th scope="col">Marca</th>
            <th scope="col">Linha</th>
            <th scope="col">Vlr Venda</th>
            <th scope="col">Estoque Mínimo</th>
            <th scope="col">Cd Interno</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="produto" items="${listaProdutos}">
                <tr>
                    <td class="cel-img-tabela-clientes">
                        <form action="${pageContext.request.contextPath}/produto/delete/${produto.id}" method="POST">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarProduto('${produto.id}', '${produto.codigoInterno}', '${produto.descricaoProduto}', '${produto.codigoBarras}', '${produto.marca.id}', '${produto.linha.id}', '${produto.caracteristica.id}', '${produto.estoqueMinimo}', '${produto.custo}', '${produto.valorVenda}', '${produto.comissao}'); return false;" title="Editar">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/produto/delete/${produto.id}')">
                                <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                            </a>
                        </form>
                    </td>
                    <td><c:out value="${produto.descricaoProduto}" /></td>
                    <td><c:out value="${produto.marca.descricaoMarca}" /></td>
                    <td><c:out value="${produto.linha.descricaoLinha}" /></td>
                    <td><c:out value="${produto.valorVenda}" /></td>
                    <td><c:out value="${produto.estoqueMinimo}" /></td>
                    <td><c:out value="${produto.codigoInterno}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
