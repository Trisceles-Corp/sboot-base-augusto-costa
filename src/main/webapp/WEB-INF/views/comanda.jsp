<%--
  Created by IntelliJ IDEA.
  User: Alexander Andrade
  Date: 30/04/2024
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Comandas Abertas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />

    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div>
    <div class="itemHeader">
        <h4>Comandas Abertas</h4>
    </div>
    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="dtoComanda" action="${pageContext.request.contextPath}/comanda/salvar" method="POST"  onsubmit="return verificarValoresPagamentosAntesDeSalvar()">
        <form:hidden path="agendamentoId" id="field_agendamentoId"/>
        <div class="row">
            <div class="form-group col-md-2">
                <form:label path="situacao" class="form-label" for="field_situacaoId">Situação:</form:label>
                <form:select path="situacao" class="form-control" id="field_situacaoId" required="required" readonly="true">
                    <option value=true label="Aberto"></option>
                    <option value=false label="Fechado"></option>
                </form:select>
            </div>
            <div class="form-group col-md-2">
                <form:label path="comandaId" class="form-label" for="field_Id">Comanda:</form:label>
                <form:input  path="comandaId" type="text" class="form-control" id="field_Id" disabled="true"/>
            </div>
            <div class="form-group col-md-4">
                <form:label path="clienteId" class="form-label" for="field_ClienteId">Cliente:</form:label>
                <form:select path="clienteId" class="form-control" id="field_ClienteId" readonly="readonly">
                    <form:options items="${listarClientes}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
                </form:select>
            </div>
            <div class="form-group col-md-4">
                <form:label path="colaboradorId" class="form-label" for="field_ColaboradorId">Colaborador:</form:label>
                <form:select path="colaboradorId" class="form-control" id="field_ColaboradorId" readonly="readonly">
                    <form:option value="" label=" Selecione "/>
                    <form:options items="${listarColaboradores}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
                </form:select>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-2">
                <form:label path="dataAgendamento" class="form-label" for="field_dataAgendamento">Data:</form:label>
                <form:input  path="dataAgendamento" type="date" class="form-control" id="field_dataAgendamento" readonly="true" />
            </div>
            <div class="form-group col-md-2">
                <form:label path="horaAgendamento" class="form-label" for="field_HoraAgendamento">Hora:</form:label>
                <form:input  path="horaAgendamento" type="text" class="form-control" id="field_HoraAgendamento" readonly="true" />
            </div>
            <div class="form-group col-md-2">
                <form:label path="valorServicos" class="form-label" for="field_valorServicos">Valor Serviços(+):</form:label>
                <form:input path="valorServicos" class="form-control" type="number" id="field_valorServicos" readonly="true" />
            </div>
            <div class="form-group col-md-2">
                <form:label path="valorProdutos" class="form-label" for="field_valorProdutos">Valor Produtos(+):</form:label>
                <form:input path="valorProdutos" class="form-control" type="number" id="field_valorProdutos" readonly="true" />
            </div>
            <div class="form-group col-md-2">
                <form:label path="valorDescontos" class="form-label" for="field_valorDescontos">Valor Descontos(-):</form:label>
                <form:input path="valorDescontos" class="form-control" type="number" id="field_valorDescontos" readonly="true" />
            </div>
            <div class="form-group col-md-2">
                <form:label path="valorComanda" class="form-label" for="field_valorComanda">Valor Comanda(=):</form:label>
                <form:input path="valorComanda" class="form-control" type="text" id="field_valorComanda" readonly="true" />
            </div>
        </div>
        <div class="row">
            <div class="mt-2" id="tabelaServicos">
                <table id="tabelaDadosServicos" class="table table-bordered table-hover table-responsive my-3">
                    <thead class="table-secondary">
                    <tr class="gridHeader">
                        <th scope="col" class="th-editar">Item</th>
                        <th scope="col">Id</th>
                        <th scope="col">Serviço</th>
                        <th scope="col">Valor</th>
                        <th scope="col">Duração</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="servico" items="${listarServiçosAgendamento}">
                        <tr>
                            <td class="cel-img-tabela-clientes">
                                <form action="${pageContext.request.contextPath}/saida/delete/${servico.id}" method="POST">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/saida/delete/${servico.id}')">
                                        <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                                    </a>
                                </form>
                            </td>
                            <td><c:out value="${servico.id}" /></td>
                            <td><c:out value="${servico.nome}" /></td>
                            <td><c:out value="${servico.valor}" /></td>
                            <td><c:out value="${servico.tempo}" /></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="mt-2" id="tabelaProduto" >
                <table id="tabelaDadosProdutos" class="table table-bordered table-hover table-responsive my-3">
                    <thead class="table-secondary">
                    <tr class="gridHeader">
                        <th scope="col" class="th-editar">Item</th>
                        <th scope="col">Id</th>
                        <th scope="col">Produto</th>
                        <th scope="col">Marca</th>
                        <th scope="col">Linha</th>
                        <th scope="col">Preço</th>
                        <th scope="col">Quantidade</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="saidaProduto" items="${listarProdutosAgendamento}">
                        <tr>
                            <td class="cel-img-tabela-clientes">
                                <form action="${pageContext.request.contextPath}/saida/delete/${saidaProduto.produtoId}" method="POST">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/saida/delete/${saidaProduto.produtoId}')">
                                        <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                                    </a>
                                </form>
                            </td>
                            <td><c:out value="${saidaProduto.produtoId}" /></td>
                            <td><c:out value="${saidaProduto.nome}" /></td>
                            <td><c:out value="${saidaProduto.marca}" /></td>
                            <td><c:out value="${saidaProduto.linha}" /></td>
                            <td><c:out value="${saidaProduto.preco}" /></td>
                            <td><c:out value="${saidaProduto.quantidade}" /></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="panel">
            <div class="headerContainer">
                <h5 class="headerTitle">Pagamentos</h5>
                <div class="headerRequired">* campos obrigatórios</div>
            </div>
            <div class="row">
                <div class="form-group col-md-2">
                    <form:label path="caixaId" class="form-label" for="field_CaixaId">Caixa:<span class="text-danger">*</span></form:label>
                    <form:select path="caixaId" class="form-control" id="field_CaixaId" required="true" >
                        <form:option value="0" label=" Selecione "/>
                        <form:options items="${listarCaixas}" itemValue="id" itemLabel="nome"/>
                    </form:select>
                </div>
                <div class="form-group col-md-2">
                    <form:label path="formaPagamentoId" class="form-label" for="field_formaPagamentoId">Forma:<span class="text-danger">*</span></form:label>
                    <form:select path="formaPagamentoId" class="form-control" id="field_formaPagamentoId" onchange="ajustarCamposFormaPagamento();">
                        <form:option value="0" label=" Selecione "/>
                        <form:options items="${listarFormaPagamentos}" itemValue="id" itemLabel="nome"/>
                    </form:select>
                </div>
                <div class="form-group col-md-2">
                    <form:label path="bandeiraId" class="form-label" for="field_bandeiraId">Informação:<span class="text-danger">*</span></form:label>
                    <form:select path="bandeiraId" class="form-control" id="field_bandeiraId">
                        <form:option value="0" label=" Selecione "/>
                        <form:options items="${listarBandeiras}" itemValue="id" itemLabel="nome"/>
                    </form:select>
                </div>
                <div class="form-group col-md-1">
                    <form:label path="parcelas" class="form-label" for="field_parcelas">Parcelas:<span class="text-danger">*</span></form:label>
                    <form:input path="parcelas" class="form-control" type="number" id="field_parcelas"/>
                </div>
                <div class="form-group col-md-2">
                    <form:label path="valorInserido" class="form-label" for="field_valorPagamento">Valor:<span class="text-danger">*</span></form:label>
                    <form:input path="valorInserido" class="form-control" type="number" id="field_valorPagamento"/>
                </div>
                <div class="form-group col-md-1">
                    <label class="form-label" for="buttonPagamento">Adicionar:</label>
                    <button type="button" class="btn btn-outline-secondary" id="buttonPagamento" onclick="adicionarPagamento()">+</button>
                </div>
                <div class="form-group col-md-2">
                    <form:label path="valorPagamento" class="form-label" for="field_valorTotalPgto">Total a Pagar:</form:label>
                    <form:input path="valorPagamento" class="form-control" type="text" id="field_valorTotalPgto" disabled="true"/>
                </div>
            </div>
            <div class="row" id="tabelaPagamentos">
                <table id="tabelaDadosPagamentos" class="table table-bordered table-hover table-responsive my-3" style="display: none">
                    <thead class="table-secondary">
                    <tr class="gridHeader">
                        <th scope="col" class="th-editar">Ações</th>
                        <th scope="col">Forma Pagamento</th>
                        <th scope="col">Informações</th>
                        <th scope="col">Parcelas</th>
                        <th scope="col">Valor</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="pagamento" items="${listarPagamentos}">
                        <tr>
                            <td class="cel-img-tabela-clientes">
                                <form action="${pageContext.request.contextPath}/saida/delete/${pagamento.id}" method="POST">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/saida/delete/${pagamento.id}')">
                                        <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                                    </a>
                                </form>
                            </td>
                            <td><c:out value="${pagamento.formaPagamento.nome}" /></td>
                            <td><c:out value="${pagamento.bandeira.nome}" /></td>
                            <td><c:out value="${pagamento.parcelas}" /></td>
                            <td><c:out value="${pagamento.valorPagamento}" /></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="mt-2">
<%--                <button type="button" class="btn btn-primary" onclick="coletarDadosPagamentos(); document.getElementById('form-cadastro').submit();">Fechar Comanda</button>--%>
                <button type="submit" class="btn btn-primary" id="salvar-cadastro">Fechar Comanda</button>
                <button type="button" class="btn btn-danger m-1" id="cancelar-cadastro" onclick="toggleCloseCadastro()">Cancelar</button>
            </div>
        </div>
    </form:form>
</div>
<div>
    <table id="tabelaComandas" class="table table-bordered table-hover table-responsive my-3">
        <thead class="table-dark">
        <tr class="gridHeader">
            <th scope="col" class="th-editar">Ações</th>
            <th scope="col">Nro.</th>
            <th scope="col">Cliente</th>
            <th scope="col">Colaborador</th>
            <th scope="col">Data Abertura</th>
            <th scope="col">Valor</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="comanda" items="${listarComandas}">
            <tr>
                <td class="cel-img-tabela-clientes">
                    <form action="${pageContext.request.contextPath}/comanda/delete/${comanda.comandaId}" method="POST">
                        <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarComanda('${pageContext.request.contextPath}', '${comanda.comandaId}', '${comanda.agendamentoId}', '${comanda.clienteId}', '${comanda.colaboradorId}', '${comanda.dataAgendamento}', '${comanda.horaAgendamento}', '${comanda.valorServicos}', '${comanda.valorProdutos}', '${comanda.valorDescontos}', '${comanda.valorComanda}', '${comanda.situacao}'); return false;" title="Editar">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/comanda/delete/${comanda.comandaId}')">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                        </a>
                    </form>
                </td>
                <th scope="row"><c:out value="${comanda.comandaId}" /></th>
                <td><c:out value="${comanda.nomeCliente}" /></td>
                <td><c:out value="${comanda.nomeColaborador}" /></td>
                <td><c:out value="${comanda.dataAgendamento}" /></td>
                <td><c:out value="${comanda.valorComanda}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
