<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String perfil = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("perfil")) {
                String encodedPerfilValue = cookie.getValue();
                perfil = URLDecoder.decode(encodedPerfilValue, StandardCharsets.UTF_8);
                break;
            }
        }
    }
%>
<html>
<head>
    <title>Agendamento</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />

    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>

    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body>
<div>
    <div class="headerContainer">
        <h4 class="headerTitle">Agendamento</h4>
        <div class="headerRequired">* campos obrigatórios</div>
    </div>
    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="dtoAgendamento" action="${pageContext.request.contextPath}/agendamento/salvar" method="POST" onsubmit="return verificarCamposAgendamentoAntesDeSalvar()" style="display: block">
        <form:hidden path="agendamento.id" id="field_Id"/>
    <div class="row">
        <div class="form-group col-md-4">
            <form:label path="agendamento.cliente.id" class="form-label" for="field_ClienteId">Cliente:<span class="text-danger">*</span></form:label>
            <form:select path="agendamento.cliente.id" class="form-control" id="field_ClienteId" required="required" >
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarClientes}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
            </form:select>
        </div>
        <div class="form-group col-md-4">
            <form:label path="agendamento.colaborador.id" class="form-label" for="field_ColaboradorId">Colaborador:<span class="text-danger">*</span></form:label>
            <form:select path="agendamento.colaborador.id" class="form-control" id="field_ColaboradorId" onchange="horariosDisponiveis(contextPath)" required="required">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarColaboradores}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
            </form:select>
        </div>
        <div class="form-group col-md-2">
            <form:label path="agendamento.dataAgendamento" class="form-label" for="inputData">Data:<span class="text-danger">*</span></form:label>
            <form:input  path="agendamento.dataAgendamento" type="date" class="form-control" id="inputData" placeholder="mm-dd-yyyy"  readonly="readonly" required="required" />
        </div>
        <div class="form-group col-md-1">
            <form:label path="agendamento.horaAgendamento" class="form-label" for="field_HoraAgendamento">Hora:<span class="text-danger">*</span></form:label>
            <form:select path="agendamento.horaAgendamento" class="form-control" id="field_HoraAgendamento" required="required">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarHorarios}" itemValue="horario" itemLabel="horario"/>
            </form:select>
        </div>
        <div class="form-group col-md-1">
            <form:label path="agendamento.duracao" class="form-label" for="field_Duracao">Duração:</form:label>
            <form:input path="agendamento.duracao" type="text" class="form-control" id="field_Duracao" readonly="true"/>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-3">
            <form:label path="servicosAgendamento.servico.id" class="form-label" for="field_ServicoId">Serviço:</form:label>
            <form:select path="servicosAgendamento.servico.id" class="form-control" id="field_ServicoId">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarServiços}" itemValue="id" itemLabel="nome"/>
            </form:select>
        </div>
        <div class="form-group col-md-1">
            <label class="form-label" for="buttonServicos">Adicionar:</label>
            <button type="button" class="btn btn-outline-secondary" id="buttonServicos" onclick="adicionarServico()">+</button>
        </div>
        <div class="form-group col-md-2">
            <form:label path="localEstoqueId" class="form-label" for="field_LocalEstoqueId">Local Estoque:<span class="text-danger">*</span></form:label>
            <form:select path="localEstoqueId" class="form-control" id="field_LocalEstoqueId">
                <form:options items="${listarLocaisEstoque}" itemValue="id" itemLabel="DescricaoLocal"/>
            </form:select>
        </div>
        <div class="form-group col-md-4">
            <form:label path="vendaProduto.produto.id" class="form-label" for="field_ProdutoId">Produto:</form:label>
            <form:select path="vendaProduto.produto.id" class="form-control" id="field_ProdutoId">
                <form:option value="" label=" Selecione "/>
                <form:options items="${listarProdutos}" itemValue="id" itemLabel="descricaoProduto"/>
            </form:select>
        </div>
        <div class="form-group col-md-1">
            <form:label path="vendaProduto.quantidade" class="form-label" for="field_Quantidade">Qtde.:</form:label>
            <form:input path="vendaProduto.quantidade" type="number" class="form-control" id="field_Quantidade" placeholder="0"/>
        </div>
        <div class="form-group col-md-1">
            <label class="form-label" for="buttonProduto">Adicionar:</label>
            <button type="button" class="btn btn-outline-secondary" id="buttonProduto" onclick="adicionarProduto()">+</button>
        </div>
        <div class="row">
            <div class="form-group col-md-2">
                <form:label path="agendamento.situacao.id" class="form-label" for="field_SituacaoId">Situação:<span class="text-danger">*</span></form:label>
                <form:select path="agendamento.situacao.id" class="form-control" id="field_SituacaoId" required="required">
                    <form:options items="${listarSituacao}" itemValue="id" itemLabel="nome"/>
                </form:select>
            </div>
        </div>
        <div class="row" id="tabelaServicos">
            <table id="tabelaDadosServicos" class="table table-bordered table-hover table-responsive my-3"  style="display: ${not empty listarServiçosAgendamento ? 'block' : 'none'}">
                <!-- Restante do código da tabela tabelaDadosServicos -->
                <thead class="table-secondary">
                <tr class="gridHeader">
                    <th scope="col" class="th-editar">Ações</th>
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

        <div class="row" id="tabelaProduto" >
            <table id="tabelaDadosProdutos" class="table table-bordered table-hover table-responsive my-3" style="display: ${not empty listarProdutosAgendamento ? 'block' : 'none'}">
                <thead class="table-secondary">
                <tr class="gridHeader">
                    <th scope="col" class="th-editar">Ações</th>
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

        <div class="mt-2">
            <button type="submit" class="btn btn-primary" id="buttonSalvar">Salvar</button>
        </div>
        </form:form>
    </div>
</div>
</body>
</html>
