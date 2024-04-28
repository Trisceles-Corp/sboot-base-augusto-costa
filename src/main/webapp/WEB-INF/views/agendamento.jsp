<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Agendamento</title>
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
        <h4>Agendamento</h4>
    </div>
    <div class="row" id="linha-botao-cadastro">
        <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2" id="btn-cadastrar" onclick="toggleFormCadastro()">Cadastrar</button>
    </div>
    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="dtoAgendamento" action="${pageContext.request.contextPath}/agendamento/salvar" method="POST">
        <form:hidden path="agendamento.id" id="field_Id"/>
    <div class="row">
        <div class="form-group col-md-4">
            <form:label path="agendamento.cliente.id" class="form-label" for="field_ClienteId">Cliente:</form:label>
            <form:select path="agendamento.cliente.id" class="form-control" id="field_ClienteId" required="required">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarClientes}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
            </form:select>
        </div>
        <div class="form-group col-md-4">
            <form:label path="agendamento.colaborador.id" class="form-label" for="field_ColaboradorId">Colaborador:</form:label>
            <form:select path="agendamento.colaborador.id" class="form-control" id="field_ColaboradorId" required="required">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarColaboradores}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
            </form:select>
        </div>
        <div class="form-group col-md-2">
            <form:label path="agendamento.dataAgendamento" class="form-label" for="inputNascimento">Data:</form:label>
            <form:input  path="agendamento.dataAgendamento" type="date" class="form-control" id="inputNascimento" placeholder="mm-dd-yyyy"  readonly="readonly" required="required" />
        </div>
        <div class="form-group col-md-1">
            <form:label path="agendamento.horaAgendamento" class="form-label" for="field_HoraAgendamento">Hora:</form:label>
            <form:select path="agendamento.horaAgendamento" class="form-control" id="field_HoraAgendamento" required="required">
                <form:option value="" label="Select"/>
                <option selected>Select</option>
                <option value="09:00" label="09:00"></option>
                <option value="09:30" label="09:30"></option>
                <option value="10:00" label="10:00"></option>
                <option value="10:30" label="10:30"></option>
                <option value="11:00" label="11:00"></option>
                <option value="11:30" label="11:30"></option>
                <option value="12:00" label="12:00"></option>
                <option value="12:30" label="12:30"></option>
                <option value="13:00" label="13:00"></option>
                <option value="13:30" label="13:30"></option>
                <option value="14:00" label="14:00"></option>
                <option value="14:30" label="14:30"></option>
                <option value="15:00" label="15:00"></option>
                <option value="15:30" label="15:30"></option>
                <option value="16:00" label="16:00"></option>
                <option value="16:30" label="16:30"></option>
                <option value="17:00" label="17:00"></option>
                <option value="17:30" label="17:30"></option>
                <option value="18:00" label="18:00"></option>
            </form:select>
        </div>
        <div class="form-group col-md-1">
            <form:label path="agendamento.duracao" class="form-label" for="field_Duracao">Duração:</form:label>
            <form:input path="agendamento.duracao" type="text" class="form-control" id="field_Duracao" readonly="true"/>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-4">
            <form:label path="servicosAgendamento.servico.id" class="form-label" for="field_ServicoId">Serviço:</form:label>
            <form:select path="servicosAgendamento.servico.id" class="form-control" id="field_ServicoId" required="required">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarServiços}" itemValue="id" itemLabel="nome"/>
            </form:select>
        </div>
        <div class="form-group col-md-4">
            <form:label path="vendaProduto.produto.id" class="form-label" for="field_ProdutoId">Produto:</form:label>
            <form:select path="vendaProduto.produto.id" class="form-control" id="field_ProdutoId">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarProdutos}" itemValue="id" itemLabel="descricaoProduto"/>
            </form:select>
        </div>
        <div class="form-group col-md-2">
            <form:label path="vendaProduto.quantidade" class="form-label" for="field_Quantidade">Qtde Produto:</form:label>
            <form:input path="vendaProduto.quantidade" type="number" class="form-control" id="field_Quantidade" placeholder="0"/>
        </div>
        <div class="form-group col-md-2">
            <form:label path="agendamento.situacao.id" class="form-label" for="field_SituacaoId">Situação:</form:label>
            <form:select path="agendamento.situacao.id" class="form-control" id="field_SituacaoId" required="required">
                <form:options items="${listarSituacao}" itemValue="id" itemLabel="nome"/>
            </form:select>
        </div>
        <div class="row">
            <div class="form-group col-md-5">
                <button type="button" class="btn btn-outline-secondary" onclick="adicionarServico()">Adicionar Serviço</button>
            </div>
            <div class="form-group col-md-4">
                <button type="button" class="btn btn-outline-secondary" onclick="adicionarProduto()">Adicionar Produto</button>
            </div>
        </div>

        <div class="row" id="tabelaServicos">
            <table id="tabelaDadosServicos" class="table table-bordered table-hover table-responsive my-3">
                <thead class="table-secondary">
                <tr class="gridHeader">
                    <th scope="col" class="th-editar">Ações</th>
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
                        <td><c:out value="${servico.nome}" /></td>
                        <td><c:out value="${servico.valor}" /></td>
                        <td><c:out value="${servico.tempo}" /></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="row" id="tabelaProduto">
            <table id="tabelaDadosProdutos" class="table table-bordered table-hover table-responsive my-3">
                <thead class="table-secondary">
                <tr class="gridHeader">
                    <th scope="col" class="th-editar">Ações</th>
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
                            <form action="${pageContext.request.contextPath}/saida/delete/${saidaProduto.id}" method="POST">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/saida/delete/${saidaProduto.id}')">
                                    <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                                </a>
                            </form>
                        </td>
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

        <div class="mt-2">
            <button type="submit" class="btn btn-primary">Salvar</button>
            <button type="button" class="btn btn-danger m-1" id="cancelar-cadastro" onclick="toggleCloseCadastro()">Cancelar</button>
        </div>
        </form:form>
    </div>
</div>
</body>
</html>
