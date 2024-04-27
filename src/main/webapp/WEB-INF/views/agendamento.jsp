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
    <div class="row" id="linha-botao-cadastro">
        <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2 " id="btn-cadastrar" onclick="toggleFormCadastro()">Cadastrar Agendamento</button>
    </div>

    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="dtoAgendamento" action="${pageContext.request.contextPath}/agendamento/salvar" method="POST" onsubmit="return verificarNomeAntesDeSalvar()">
        <form:hidden path="agendamento.id" id="field_Id"/>
    <div class="row">
        <div class="form-group col-md-4">
            <form:label path="agendamento.cliente.id" class="form-label">Cliente:</form:label>
            <form:select path="agendamento.cliente.id" class="form-control" id="field_ClienteId" required="required">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarClientes}" itemValue="id" itemLabel="Nome"/>
            </form:select>
        </div>
        <div class="form-group col-md-4">
            <form:label path="agendamento.colaborador.id" class="form-label">Colaborador:</form:label>
            <form:select path="agendamento.colaborador.id" class="form-control" id="field_ColaboradorId" required="required">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarColaboradores}" itemValue="id" itemLabel="Nome"/>
            </form:select>
        </div>
        <div class="form-group col-md-2">
            <form:label path="agendamento.dataAgendamento" class="form-label" for="inputNascimento">Data:</form:label>
            <form:input  path="agendamento.dataAgendamento" type="date" class="form-control" id="inputNascimento" placeholder="mm-dd-yyyy"  readonly="readonly" required="required" />
        </div>
        <div class="form-group col-md-2">
            <form:label path="agendamento.horaAgendamento" class="form-label" for="text-input">Hora:</form:label>
            <form:input path="agendamento.horaAgendamento" class="form-control" type="time" id="field_HoraAgendamento" min="09:00" max="18:00" required="required"/>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-4">
            <form:label path="servicosAgendamento.servico.id" class="form-label">Serviço:</form:label>
            <form:select path="servicosAgendamento.servico.id" class="form-control" id="field_ServicoId">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarServiços}" itemValue="id" itemLabel="nome"/>
            </form:select>
        </div>
        <div class="form-group col-md-4">
            <form:label path="vendaProduto.produto.id" class="form-label">Produto:</form:label>
            <form:select path="vendaProduto.produto.id" class="form-control" id="field_ProdutoId">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarProdutos}" itemValue="id" itemLabel="descricaoProduto"/>
            </form:select>
        </div>
        <div class="form-group col-md-2">
            <form:label path="agendamento.situacao.id" class="form-label">Situação:</form:label>
            <form:select path="agendamento.situacao.id" class="form-control" id="field_SituacaoId">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarSituacao}" itemValue="id" itemLabel="nome"/>
            </form:select>
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
                <th scope="col">Preço</th>
                <th scope="col">Mínimo</th>
                <th scope="col">Codigo</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="agendamento" items="${listaProdutos}">
                <tr>
                    <td class="cel-img-tabela-clientes">
                        <form action="${pageContext.request.contextPath}/agendamento/delete/${agendamento.id}" method="POST">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarProduto('${agendamento.id}', '${agendamento.codigoInterno}', '${agendamento.descricaoProduto}', '${agendamento.codigoBarras}', '${agendamento.marca.id}', '${agendamento.categoria.id}', '${agendamento.linha.id}', '${agendamento.caracteristica.id}', '${agendamento.estoqueMinimo}', '${agendamento.custo}', '${agendamento.valorVenda}', '${agendamento.comissao}'); return false;" title="Editar">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/agendamento/delete/${agendamento.id}')">
                                <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                            </a>
                        </form>
                    </td>
                    <td><c:out value="${agendamento.descricaoProduto}" /></td>
                    <td><c:out value="${agendamento.marca.descricaoMarca}" /></td>
                    <td><c:out value="${agendamento.linha.descricaoLinha}" /></td>
                    <td><c:out value="${agendamento.valorVenda}" /></td>
                    <td><c:out value="${agendamento.estoqueMinimo}" /></td>
                    <td><c:out value="${agendamento.codigoInterno}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
