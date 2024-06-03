<%--
  Created by IntelliJ IDEA.
  User: ck_aa
  Date: 22/04/2024
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Augusto Costa</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>

</head>
<body>
<div>
    <div class="headerContainer">
        <h4 class="headerTitle">Solicitações de Saída</h4>
        <div class="headerRequired">* campos obrigatórios</div>
    </div>
    <div class="row" id="linha-botao-cadastro">
        <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2" id="btn-cadastrar" onclick="toggleFormCadastro()">Cadastrar</button>
    </div>

    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="dtoSaida" action="${pageContext.request.contextPath}/saida/salvar" method="POST">
        <div class="row">
            <div class="form-group col-md-1">
                <form:label path="id" class="form-label" for="field_Id">Solicitação:</form:label>
                <form:input path="id" class="form-control" type="text" id="field_Id" readonly="true"/>
            </div>
            <div class="form-group col-md-2">
                <form:label path="solicitanteId" class="form-label" for="field_SolicitanteId">Solicitante:<span class="text-danger">*</span></form:label>
                <form:select path="solicitanteId" class="form-control" id="field_SolicitanteId" required="required" >
                    <form:option value="" label=" Selecione "/>
                    <form:options items="${listarSolicitante}" itemValue="id" itemLabel="nome"/>
                </form:select>
            </div>
            <div class="form-group col-md-4">
                <form:label path="localEstoqueId" class="form-label" for="field_LocalEstoqueId">Local Estoque:<span class="text-danger">*</span></form:label>
                <form:select path="localEstoqueId" class="form-control" id="field_LocalEstoqueId" required="required" >
                    <form:option value="" label=" Selecione "/>
                    <form:options items="${listarLocalEstoque}" itemValue="id" itemLabel="descricaoLocal"/>
                </form:select>
            </div>
            <div class="form-group col-md-2">
                <form:label path="valorTotal" class="form-label" for="field_ValorTotal">Valor Solicitação:</form:label>
                <form:input path="valorTotal" class="form-control" type="number" id="field_ValorTotal" readonly="true"/>
            </div>
            <div class="form-group col-md-2">
                <form:label path="dataCriacao" class="form-label" for="field_DataCriacao">Data Solicitação:</form:label>
                <form:input path="dataCriacao" class="form-control" type="text" id="field_DataCriacao" disabled="true" />
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <form:label path="produtoId" class="form-label" for="field_ProdutoId">Produto:</form:label>
                <form:select path="produtoId" class="form-control" id="field_ProdutoId">
                    <form:option value="0" label=" Selecione "/>
                    <form:options items="${listarProdutos}" itemValue="id" itemLabel="descricaoProduto"/>
                </form:select>
            </div>
            <div class="form-group col-md-1">
                <form:label path="quantidade" class="form-label" for="field_Quantidade">Qtde.:</form:label>
                <form:input path="quantidade" type="number" class="form-control" id="field_Quantidade" placeholder="0"/>
            </div>
            <div class="form-group col-md-1">
                <label class="form-label" for="buttonCompras">Adicionar:</label>
                <button type="button" class="btn btn-outline-secondary" id="buttonCompras" onclick="adicionarSaidaProduto()">+</button>
            </div>
        </div>
        <div class="row" id="tabelaSaidaProduto" style="padding: 20px">
            <table id="tabelaDadosProdutos" class="table table-bordered table-hover table-responsive my-3">
                <thead class="table-secondary">
                <tr class="gridHeader">
                    <th scope="col" class="th-editar">Ações</th>
                    <th scope="col">Id</th>
                    <th scope="col">Produto</th>
                    <th scope="col">Valor Uniário</th>
                    <th scope="col">Quantidade</th>
                    <th scope="col">Total Produto</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="saidaProduto" items="${listarSaidaProdutos}">
                    <tr>
                        <td class="cel-img-tabela-clientes">
                            <form action="${pageContext.request.contextPath}/saida/delete/${saidaProduto.id}" method="POST">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/saida/delete/${saidaProduto.id}')">
                                    <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                                </a>
                            </form>
                        </td>
                        <td><c:out value="${saidaProduto.produto.id}" /></td>
                        <td><c:out value="${saidaProduto.produto.descricaoProduto}" /></td>
                        <td><c:out value="${saidaProduto.valorUnitario}" /></td>
                        <td><c:out value="${saidaProduto.quantidade}" /></td>
                        <td><c:out value="${saidaProduto.valorTotal}" /></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="mt-2">
            <button type="submit" class="btn btn-primary" id="salvar-cadastro" onclick="coletarDadosFormularioSaidas(); document.getElementById('form-cadastro').submit();">Salvar</button>
            <button type="button" class="btn btn-danger m-1" id="cancelar-cadastro" onclick="toggleCloseCadastro()">Cancelar</button>
            <button type="button" class="btn btn-success m-1" id="finalizar-cadastro" onclick="finalizarSaida('${pageContext.request.contextPath}', document.getElementById('field_Id').value)">Finalizar</button>
        </div>
    </form:form>
</div>

<div id="tabelaSaidas">
    <table id="tabelaDadosSaidas" class="table table-bordered table-hover table-responsive my-3">
        <thead class="table-dark">
        <tr class="gridHeader">
            <th scope="col" class="th-editar">Ações</th>
            <th scope="col">Solicitação</th>
            <th scope="col">Solicitante</th>
            <th scope="col">Local Estoque</th>
            <th scope="col">Valor</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="saida" items="${listarSaidas}">
            <tr>
                <td class="cel-img-tabela-clientes">
                    <form action="${pageContext.request.contextPath}/saida/delete/${saida.id}" method="POST">
                        <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarSaidas('${pageContext.request.contextPath}', '${saida.id}', '${saida.localEstoque.id}', '${saida.solicitante.id}', '${saida.valorTotal}', '${saida.dataCriacao}'); return false;" title="Editar">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/saida/delete/${saida.id}')">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                        </a>
                    </form>
                </td>
                <th scope="row"><c:out value="${saida.id}" /></th>
                <td><c:out value="${saida.solicitante.nome}" /></td>
                <td><c:out value="${saida.localEstoque.descricaoLocal}" /></td>
                <td><c:out value="${saida.valorTotal}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
