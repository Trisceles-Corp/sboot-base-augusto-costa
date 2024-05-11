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
    <script type="text/javascript">
        window.onload = function() {
            const hoje = new Date();
            const primeiroDia = new Date(hoje.getFullYear(), hoje.getMonth(), 1);
            const ultimoDia = new Date(hoje.getFullYear(), hoje.getMonth() + 1, 0);

            const formatarData = (data) => {
                let mes = '' + (data.getMonth() + 1),
                    dia = '' + data.getDate(),
                    ano = data.getFullYear();

                if (mes.length < 2)
                    mes = '0' + mes;
                if (dia.length < 2)
                    dia = '0' + dia;

                return [ano, mes, dia].join('-');
            };

            document.getElementById('searchDataInicial').value = formatarData(primeiroDia);
            document.getElementById('searchDataFinal').value = formatarData(ultimoDia);
        };
    </script>
</head>
<body>
<div>
    <div class="headerContainer">
        <h4 class="headerTitle">Comissões</h4>
        <div class="headerRequired">* campos obrigatórios</div>
    </div>
    <!-- formulário de cadastro -->
    <form:form class="form-cadastro my-2" id="form-cadastro" style="display: block"  modelAttribute="dtoComanda" action="${pageContext.request.contextPath}/caixa/salvar" method="POST">
        <div class="row">
            <div class="panel col-md-4" id="panel-pesquisa" >
                <div class="row">
                    <div class="form-group col-md-12">
                        <form:label path="colaboradorId" class="form-label" for="field_ColaboradorId">Profissional:<span class="text-danger">*</span></form:label>
                        <form:select path="colaboradorId" class="form-control" id="field_ColaboradorId" required="true">
                            <form:option value="0" label=" Selecione "/>
                            <form:options items="${listarColaboradores}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
                        </form:select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="form-label" for="searchDataInicial">Data Inicial:<span class="text-danger">*</span></label>
                        <input type="date" class="form-control" id="searchDataInicial" placeholder="mm-dd-yyyy"  required="required" />
                    </div>
                    <div class="form-group col-md-6">
                        <label  class="form-label" for="searchDataFinal">Data Final:<span class="text-danger">*</span></label>
                        <input type="date" class="form-control" id="searchDataFinal" placeholder="mm-dd-yyyy"  required="required" />
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label  class="form-label" for="salvar-cadastro">Pesquisar:</label>
                        <button type="button" class="btn btn-primary" id="salvar-cadastro" onclick="pesquisarComissoes('${pageContext.request.contextPath}', document.getElementById('field_ColaboradorId').value, document.getElementById('searchDataInicial').value, document.getElementById('searchDataFinal').value); return false;">Comissões</button>                    </div>
                </div>
            </div>
            <div class="panel col-md-8" id="panel-resultado" >
                <div class="row">
                    <div class="form-group col-md-8">
                        <form:label path="colaboradorId" class="form-label" for="inputColaborador">Colaborador:</form:label>
                        <form:select path="colaboradorId" class="form-control" id="inputColaborador" disabled="true">
                            <form:option value="0" label=" Selecione "/>
                            <form:options items="${listarColaboradores}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
                        </form:select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label class="form-label" for="inputDataInicial">Data Inicial:</label>
                        <input class="form-control" type="text" id="inputDataInicial" readonly="readonly"/>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="form-label" for="inputDataFinal">Data Final:</label>
                        <input class="form-control" type="text" id="inputDataFinal" readonly="readonly"/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label class="form-label" for="inputTotalServicos">Total Serviços:</label>
                        <input class="form-control" type="text" id="inputTotalServicos" readOnly="readonly"/>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="form-label" for="inputTotalDescontos">Total Descontos:</label>
                        <input class="form-control" type="text" id="inputTotalDescontos" readonly="readonly"/>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="form-label" for="inputTotalComissoes">Total Comissões:</label>
                        <input class="form-control" type="text" id="inputTotalComissoes" readonly="readonly"/>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
</div>
<div>
    <table id="tabelaComissoes" class="table table-bordered table-hover table-responsive my-12" style="display: none">
        <thead class="table-dark">
        <tr class="gridHeader">
            <th scope="col" class="th-editar">Item</th>
            <th scope="col">Agendamento</th>
            <th scope="col">Data</th>
            <th scope="col">Hora</th>
            <th scope="col">Cliente</th>
            <th scope="col">Serviços</th>
            <th scope="col">Descontos</th>
            <th scope="col">Comissão</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="comissao" items="${listarComissoes}">
            <tr>
                <td class="cel-img-tabela-clientes">
                    <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" title="Commisão">
                </td>
                <th scope="row"><c:out value="${comissao.agendamentoId}" /></th>
                <td><c:out value="${comissao.dataAgendamento}" /></td>
                <td><c:out value="${comissao.horaAgendamento}" /></td>
                <td><c:out value="${comissao.nomeCliente}" /></td>
                <td><c:out value="${comissao.valorServicos}" /></td>
                <td><c:out value="${comissao.valorDescontos}" /></td>
                <td><c:out value="${comissao.valorComissao}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
