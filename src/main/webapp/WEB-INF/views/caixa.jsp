<%--
  Created by IntelliJ IDEA.
  User: Alexander Andrade
  Date: 02/05/2024
  Time: 16:29
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
        <h4>Caixas</h4>
    </div>
    <div class="row" style="padding: 15px" id="linha-botao-cadastro">
        <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2 " id="btn-cadastrar" onclick="toggleFormCadastro()">Abrir Caixa</button>
    </div>
    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" style="width: 50%" id="form-cadastro" modelAttribute="tblCaixa" action="${pageContext.request.contextPath}/caixa/salvar" method="POST" onsubmit="return verificarNomeAntesDeSalvar()">
        <div class="panel" id="panel-cadastro">
            <div class="itemRequired">* campos obrigatórios</div>
            <form:hidden path="id" id="field_Id"/>
            <div class="row">
                <div class="form-group col-md-4">
                    <form:label path="nome" class="form-label" for="field_Name">Caixa:</form:label>
                    <form:input path="nome" class="form-control" type="text" id="field_Name" disabled="true"/>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="dataAbertura" class="form-label" for="field_DataAbertura">Abertura:</form:label>
                    <form:input path="dataAbertura" class="form-control" type="text" id="field_DataAbertura" required="required" readonly="true"/>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="dataFechamento" class="form-label" for="field_DataFechamento">Fechamento:</form:label>
                    <form:input path="dataFechamento" class="form-control" type="text" id="field_DataFechamento" readonly="true"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <form:label path="responsavelAbertura.id" class="form-label" for="field_ResponsavelAbertura">Responsável Abertura:<span class="text-danger">*</span></form:label>
                    <form:select path="responsavelAbertura.id" class="form-control" id="field_ResponsavelAbertura"  required="required">
                        <form:option value="" label=" Selecione "/>
                        <form:options items="${listarColaboradores}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
                    </form:select>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <form:label path="responsavelAbertura.email" class="form-label" for="field_Email">Email:</form:label>
                    <form:input path="responsavelAbertura.email" class="form-control" type="text" id="field_Email" readonly="true"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-10">
                    <form:label path="responsavelFechamento.id" class="form-label" for="field_ResponsavelFechamento">Responsável Fechamento:</form:label>
                    <form:select path="responsavelFechamento.id" class="form-control" id="field_ResponsavelFechamento">
                        <form:option value="" label=" Selecione "/>
                        <form:options items="${listarColaboradores}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
                    </form:select>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-4">
                    <form:label path="valorAbertura" class="form-label" for="field_ValorAbertura">Valor Abertura:<span class="text-danger">*</span></form:label>
                    <form:input path="valorAbertura" class="form-control" type="text" id="field_ValorAbertura" required="required"/>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="valorFechamento" class="form-label" for="field_ValorFechamento">Valor Fechamento:<span class="text-danger">*</span></form:label>
                    <form:input path="valorFechamento" class="form-control" type="text" id="field_ValorFechamento" required="required"/>
                </div>
            </div>
            <div class="row">
                <div class="mt-2">
                    <button type="submit" class="btn btn-primary">Fechar</button>
                    <button type="button" class="btn btn-danger m-1" id="cancelar-cadastro" onclick="toggleCloseCadastro()">Cancelar</button>
                </div>
            </div>
        </div>
    </form:form>
</div>
<div>
    <table id="tabelaProdutos" class="table table-bordered table-hover table-responsive my-3">
        <thead class="table-dark">
        <tr class="gridHeader">
            <th scope="col" class="th-editar">Ações</th>
            <th scope="col">Caixa:</th>
            <th scope="col">Resp. Abertura:</th>
            <th scope="col">Abertura:</th>
            <th scope="col">Valor Inicial</th>
            <th scope="col">Valor Final</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="caixa" items="${listarCaixas}">
            <tr>
                <td class="cel-img-tabela-clientes">
                    <form action="${pageContext.request.contextPath}/caixa/delete/${caixa.id}" method="POST">
                        <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarCaixa('${caixa.id}', '${caixa.nome}', '${caixa.responsavelAbertura.id}', '${caixa.responsavelAbertura.email}', '${caixa.dataAbertura}', '${caixa.valorAbertura}', '${caixa.responsavelFechamento.nome}', '${caixa.dataFechamento}', '${caixa.valorFechamento}'); return false;" title="Editar">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/caixa/delete/${caixa.id}')">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                        </a>
                    </form>
                </td>
                <td><c:out value="${caixa.nome}" /></td>
                <td><c:out value="${caixa.responsavelAbertura.nome}" /></td>
                <td><c:out value="${caixa.dataAbertura}" /></td>
                <td><c:out value="${caixa.valorAbertura}" /></td>
                <td><c:out value="${caixa.valorFechamento}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
