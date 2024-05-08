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
    <div class="headerContainer">
        <h4 class="headerTitle">Caixas Abertos</h4>
        <div class="headerRequired">* campos obrigatórios</div>
    </div>
    <div class="row" style="padding: 15px" id="linha-botao-cadastro">
        <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2 " id="btn-cadastrar" onclick="toggleFormCadastroCaixa()">Abrir Caixa</button>
    </div>
    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" style="width: 50%" id="form-cadastro" modelAttribute="dtoCaixa" action="${pageContext.request.contextPath}/caixa/salvar" method="POST" onsubmit="return verificarNomeAntesDeSalvar()">
        <div class="panel" id="panel-cadastro">
            <form:hidden path="caixaId" id="field_Id"/>
            <form:hidden path="nomeIndice" id="field_NomeIndice"/>
            <form:hidden path="dataAbertura" id="field_DataAberturaCompleta"/>
            <form:hidden path="dataCriacao" id="field_DataCriacao"/>
            <form:hidden path="criadoPor" id="field_CriadoPor"/>
            <div class="row">
                <div class="form-group col-md-4">
                    <form:label path="nome" class="form-label" for="field_Name">Caixa:</form:label>
                    <form:input path="nome" class="form-control" type="text" id="field_Name" readOnly="true"/>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="data" class="form-label" for="field_DataAbertura">Data Abertura:</form:label>
                    <form:input path="data" class="form-control" type="date" id="field_DataAbertura" required="required" readonly="true"/>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="hora" class="form-label" for="field_HoraAbertura">Hora Abertura:</form:label>
                    <form:input path="hora" class="form-control" type="text" id="field_HoraAbertura" readonly="true"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-8">
                    <form:label path="responsavelAbertura" class="form-label" for="field_ResponsavelAbertura">Responsável Abertura:<span class="text-danger">*</span></form:label>
                    <form:select path="responsavelAbertura" class="form-control" id="field_ResponsavelAbertura"  required="required">
                        <form:option value="0" label=" Selecione "/>
                        <form:options items="${listarColaboradores}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
                    </form:select>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="valorAbertura" class="form-label" for="field_ValorAbertura">Valor Abertura:<span class="text-danger">*</span></form:label>
                    <form:input path="valorAbertura" class="form-control" type="text" id="field_ValorAbertura" required="required"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <form:label path="email" class="form-label" for="field_Email">Email:</form:label>
                    <form:input path="email" class="form-control" type="text" id="field_Email" readonly="true"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-8">
                    <form:label path="responsavelFechamento" class="form-label" for="field_ResponsavelFechamento">Responsável Fechamento:</form:label>
                    <form:select path="responsavelFechamento" class="form-control" id="field_ResponsavelFechamento" onchange="ajustarCamposCaixaFechamento();">
                        <form:option value="0" label=" Selecione "/>
                        <form:options items="${listarColaboradores}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
                    </form:select>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="valorProvisorio" class="form-label" for="field_ValorProvisorio">Valor Provisorio:<span class="text-danger">*</span></form:label>
                    <form:input path="valorProvisorio" class="form-control" type="text" id="field_ValorProvisorio" readOnly="true"/>
                </div>
            </div>
            <div class="row">
                <div class="mt-2">
                    <button type="submit" class="btn btn-primary" id="salvar-cadastro" disabled="disabled" >Salvar</button>
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
            <th scope="col">Data:</th>
            <th scope="col">Hora:</th>
            <th scope="col">Abertura</th>
            <th scope="col">Credito</th>
            <th scope="col">Debito</th>
            <th scope="col">Dinheiro</th>
            <th scope="col">Pix</th>
            <th scope="col">Provisorio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="caixa" items="${listarCaixas}">
            <tr>
                <td class="cel-img-tabela-clientes">
                    <form action="${pageContext.request.contextPath}/caixa/delete/${caixa.caixaId}" method="POST">
                        <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarCaixa('${caixa.caixaId}', '${caixa.nome}', '${caixa.nomeIndice}', '${caixa.responsavelAbertura}', '${caixa.email}', '${caixa.dataAbertura}', '${caixa.data}', '${caixa.hora}', '${caixa.valorAbertura}', '${caixa.valorProvisorio}', '${caixa.dataCriacao}', '${caixa.criadoPor}'); return false;" title="Editar">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/caixa/delete/${caixaId}')">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                        </a>
                    </form>
                </td>
                <td><c:out value="${caixa.nome}" /></td>
                <td><c:out value="${caixa.nomeRespAbertura}" /></td>
                <td><c:out value="${caixa.data}" /></td>
                <td><c:out value="${caixa.hora}" /></td>
                <td><c:out value="${caixa.valorAbertura}" /></td>
                <td><c:out value="${caixa.credito}" /></td>
                <td><c:out value="${caixa.debito}" /></td>
                <td><c:out value="${caixa.dinheiro}" /></td>
                <td><c:out value="${caixa.pix}" /></td>
                <td><c:out value="${caixa.valorProvisorio}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
