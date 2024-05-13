<%--
  Created by IntelliJ IDEA.
  User: ck_aa
  Date: 13/05/2024
  Time: 08:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Augusto Csosta Spa</title>
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
        <h4 class="headerTitle">Entrada e Saída Financeiro</h4>
        <div class="headerRequired">* campos obrigatórios</div>
    </div>
    <div class="row">
        <div class="panel col-md-6" id="panel-pesquisa" >
            <div class="row">
                <div class="form-group col-md-4">
                    <label class="form-label" for="searchDataInicial">Data Inicial:<span class="text-danger">*</span></label>
                    <input type="date" class="form-control" id="searchDataInicial" placeholder="mm-dd-yyyy"  required="required" />
                </div>
                <div class="form-group col-md-4">
                    <label  class="form-label" for="searchDataFinal">Data Final:<span class="text-danger">*</span></label>
                    <input type="date" class="form-control" id="searchDataFinal" placeholder="mm-dd-yyyy"  required="required" />
                </div>
                <div class="form-group col-md-4">
                    <label  class="form-label" for="btn-pesquisa">Pesquisa:</label>
                    <button type="button" class="form-control btn btn-primary" id="btn-pesquisa" onclick="pesquisarMovimentacoes('${pageContext.request.contextPath}', document.getElementById('searchDataInicial').value, document.getElementById('searchDataFinal').value); return false;">Buscar</button>
                </div>
            </div>
        </div>
        <div class="panel col-md-6" id="panel-resultado" >
            <div class="row">
                <div class="form-group col-md-6">
                    <label class="form-label" for="searchDataInicial">Total de Entradas:</label>
                    <input type="number" class="form-control" id="resultTotalEntradas" readonly="readonly" />
                </div>
                <div class="form-group col-md-6">
                    <label  class="form-label" for="searchDataFinal">Total de Saídas</label>
                    <input type="number" class="form-control" id="resultTotalSaidas" readonly="readonly" />
                </div>
            </div>
        </div>
    </div>
    <div class="row" id="linha-botao-cadastro" style="padding: 20px">
        <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2 " id="btn-cadastrar" onclick="toggleFormCadastro()">Novo Registro</button>
    </div>

    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="tblCaixaMovimentacao" action="${pageContext.request.contextPath}/caixamovimentacao/salvar" method="POST" onsubmit="return verificarValoresMovimentacaoAntesDeSalvar()">
        <form:hidden path="id" id="field_Id"/>
        <div class="row">
            <div class="form-group col-md-2">
                <form:label path="caixa.id" class="form-label" for="field_CaixaId">Caixa:<span class="text-danger">*</span></form:label>
                <form:select path="caixa.id" class="form-control" id="field_CaixaId" required="true" >
                    <form:option value="0" label=" Selecione "/>
                    <form:options items="${listarCaixas}" itemValue="id" itemLabel="nome"/>
                </form:select>
            </div>
            <div class="form-group col-md-2">
                <form:label path="tipoMovimentacao.id" class="form-label" for="field_TipoMovimentacaoId">Tipo:<span class="text-danger">*</span></form:label>
                <form:select path="tipoMovimentacao.id" class="form-control" id="field_TipoMovimentacaoId" required="true" >
                    <form:option value="0" label=" Selecione "/>
                    <form:options items="${listarTipoMovimentacao}" itemValue="id" itemLabel="descricaoMovimentacao"/>
                </form:select>
            </div>
            <div class="form-group col-md-2">
                <form:label path="formaPagamento.id" class="form-label" for="field_formaPagamentoId">Forma:<span class="text-danger">*</span></form:label>
                <form:select path="formaPagamento.id" class="form-control" id="field_formaPagamentoId"  required="true">
                    <form:option value="0" label=" Selecione "/>
                    <form:options items="${listarFormasPagamento}" itemValue="id" itemLabel="nome"/>
                </form:select>
            </div>
            <div class="form-group col-md-4">
                <form:label path="criadoPor" class="form-label" for="field_ColaboradorId">Responsavel:<span class="text-danger">*</span></form:label>
                <form:select path="criadoPor" class="form-control" id="field_ColaboradorId"  required="true">
                    <form:option value="0" label=" Selecione "/>
                    <form:options items="${listarColaboradores}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
                </form:select>
            </div>
            <div class="form-group col-md-2">
                <form:label path="valorMovimentacao" class="form-label" for="field_valorMovimentacao">Valor:<span class="text-danger">*</span></form:label>
                <form:input path="valorMovimentacao" class="form-control" type="number" id="field_valorMovimentacao" required="true" />
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <form:label path="observacao" class="form-label" for="field_observacao">Justificativa:<span class="text-danger">*</span></form:label>
                <form:textarea path="observacao" class="form-control" rows="5" id="field_observacao" required="true" />
            </div>
        </div>
        <div class="mt-2">
            <button type="submit" class="btn btn-primary">Salvar</button>
            <button type="button" class="btn btn-danger m-1" id="cancelar-cadastro" onclick="toggleCloseCadastro()">Cancelar</button>
        </div>
    </form:form>
</div>
<div>
    <table id="tabelaMovimentacao" class="table table-bordered table-hover table-responsive my-3">
        <thead class="table-dark">
        <tr class="gridHeader">
            <th scope="col" class="th-editar">Ações</th>
            <th scope="col">Data</th>
            <th scope="col">Caixa</th>
            <th scope="col">Tipo</th>
            <th scope="col">Forma</th>
            <th scope="col">Valor</th>
            <th scope="col">Justificativa</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="movimentacao" items="${listarMovimentacoes}">
            <tr>
                <td class="cel-img-tabela-clientes">
                    <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarCaixaMovimentacao('${movimentacao.id}', '${movimentacao.caixa.id}', '${movimentacao.tipoMovimentacao.id}', '${movimentacao.formaPagamento.id}', '${movimentacao.criadoPor}', '${movimentacao.valorMovimentacao}', '${movimentacao.observacao}'); return false;" title="Editar">
                </td>
                <td><c:out value="${movimentacao.dataCriacao}" /></td>
                <td><c:out value="${movimentacao.caixa.nome}" /></td>
                <td><c:out value="${movimentacao.tipoMovimentacao.descricaoMovimentacao}" /></td>
                <td><c:out value="${movimentacao.formaPagamento.nome}" /></td>
                <td><c:out value="${movimentacao.valorMovimentacao}" /></td>
                <td><c:out value="${movimentacao.observacao}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
