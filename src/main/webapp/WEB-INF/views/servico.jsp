<%--
  Created by IntelliJ IDEA.
  User: Alexander Andrade
  Date: 10/04/2024
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
    <div class="itemHeader">
        <h4>Serviços</h4>
    </div>
    <div class="row" id="linha-botao-cadastro">
        <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2" id="btn-cadastrar" onclick="toggleFormCadastro()">Cadastrar Serviço</button>
    </div>

    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="tblServico" action="${pageContext.request.contextPath}/servico/salvar" method="POST">
        <form:hidden path="id" id="field_Id"/>
        <div class="row">
            <div class="form-group col-md-5">
                <form:label path="nome" class="form-label" for="text-input">Nome:</form:label>
                <form:input path="nome" class="form-control" type="text" id="field_Nome" required="required" />
            </div>
            <div class="form-group col-md-1">
                <form:label path="tempo" class="form-label" for="text-input">Tempo:</form:label>
                <form:input path="tempo" class="form-control" type="text" id="field_Tempo" required="required" placeholder="00:30"/>
            </div>
            <div class="form-group col-md-2">
                <form:label path="valor" class="form-label" for="text-input">Valor:</form:label>
                <form:input path="valor" class="form-control" type="text" id="field_Valor" required="required"/>
            </div>
            <div class="form-group col-md-2">
                <form:label path="desconto" class="form-label" for="text-input">Desconto:</form:label>
                <form:input path="desconto" class="form-control" type="text" id="field_Desconto" />
            </div>
            <div class="form-group col-md-2">
                <form:label path="comissao" class="form-label" for="text-input">Comissão:</form:label>
                <form:input path="comissao" class="form-control" type="text" id="field_Comissao" />
            </div>
        </div>
        <div>
            <div class="form-group col-md-12">
                <form:label path="observacao" class="form-label" for="text-input">Observacao:</form:label>
                <form:textarea path="observacao" class="form-control" type="text" id="field_Observacao" />
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
            <th scope="col">Nome</th>
            <th scope="col">Tempo</th>
            <th scope="col">Valor</th>
            <th scope="col">Desconto</th>
            <th scope="col">Comissao</th>
            <th scope="col">Observação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="servico" items="${listaServicos}">
            <tr>
                <td class="cel-img-tabela-clientes">
                    <form action="${pageContext.request.contextPath}/servico/delete/${servico.id}" method="POST">
                        <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarServico('${servico.id}', '${servico.nome}', '${servico.tempo}', '${servico.valor}', '${servico.desconto}', '${servico.comissao}', '${servico.observacao}'); return false;" title="Editar">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/servico/delete/${servico.id}')">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                        </a>
                    </form>
                </td>
                <td><c:out value="${servico.nome}" /></td>
                <td><c:out value="${servico.tempo}" /></td>
                <td><c:out value="${servico.valor}" /></td>
                <td><c:out value="${servico.desconto}" /></td>
                <td><c:out value="${servico.comissao}" /></td>
                <td><c:out value="${servico.observacao}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
