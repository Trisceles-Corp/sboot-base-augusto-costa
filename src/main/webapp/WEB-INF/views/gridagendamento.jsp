<%--
  Created by IntelliJ IDEA.
  User: Alexander Andrade
  Date: 23/05/2024
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
<div class="row mx-0 my-2" style="width: 100%">
    <input type="date" class="input-date-agenda col-md-2 p-1" id="dataAgenda" onchange="atualizarGridAgendamento(contextPath + '/gridagendamento')" placeholder="mm-dd-yyyy" >
    <div class="col-md-10">
        <div class="botoes-agenda">
            <button type="button" class="btn btn-outline-primary" onclick="carregarConteudo(contextPath + '/agendamento')" id="btn-agendar">Agendar cliente</button>
            <button type="button" class="btn btn-outline-secondary" onclick="carregarConteudo(contextPath + '/bloqueio')" id="btn-bloqueio">Bloquear horário</button>
        </div>
    </div>
</div>
<div class="row">
    <div class="panel col-md-2" id="panel-pesquisa">
        <div class="form-group col-md-12">
            <form class="form-group col-md-12" name="theForm">
                <div class="form-group col-md-12">
                    <input class="form-check-input" type="checkbox" id="barbeiros" name="theGroup"  value="option2" onClick="clearGroup(this);">
                    <span style="padding: 10px">Barbeiros</span>
                </div>
                <div class="form-group col-md-12">
                    <input class="form-check-input" type="checkbox" id="cabeleireiros" name="theGroup" value="option2" onClick="clearGroup(this);">
                    <span style="padding: 10px">Cabeleireiros</span>
                </div>
                <div class="form-group col-md-12">
                    <input class="form-check-input" type="checkbox" id="manicures" name="theGroup" value="option2" onClick="clearGroup(this);">
                    <span style="padding: 10px">Manicures</span>
                </div>
                <div class="form-group col-md-12">
                    <input class="form-check-input" type="checkbox" id="Todos" name="theGroup" value="option2" onClick="clearGroup(this);">
                    <span style="padding: 10px">Todos</span>
                </div>
            </form>
        </div>
        <div>
            <hr>
        </div>
        <div class="form-group col-md-12">
            <div class="color-box red"><br></div>
            <span style="padding: 10px">Aberta</span>
        </div>
        <div class="form-group col-md-12">
            <div class="color-box blue"></div>
            <span style="padding: 10px">Finalizada</span>
        </div>
        <div class="form-group col-md-12">
            <div class="color-box green"></div>
            <span style="padding: 10px">Agendada</span>
        </div>
        <div class="form-group col-md-12">
            <div class="color-box orange"></div>
            <span style="padding: 10px">Em espera</span>
        </div>
    </div>
<%--    <div class="panel col-md-10" id="panel-resultado">--%>
<%--        <div class="row">--%>
<%--            <table id="tabelaGridAgendamento" class="table table-bordered table-hover table-responsive my-3">--%>
<%--                <thead class="table-secondary">--%>
<%--                <tr class="gridHeader">--%>
<%--                    <th scope="col" class="th-editar">Horário</th>--%>
<%--                    <c:forEach var="colaborador" items="${listarAgendamentos[0].colaboradores.keySet()}">--%>
<%--                        <th scope="col">${colaborador}</th>--%>
<%--                    </c:forEach>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach var="item" items="${listarAgendamentos}">--%>
<%--                    <tr>--%>
<%--                        <td><c:out value="${item.horario}" /></td>--%>
<%--                        <c:forEach var="colaborador" items="${item.colaboradores}">--%>
<%--                            <td><c:out value="${colaborador.value}" /></td>--%>
<%--                        </c:forEach>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
</div>
<div class="row'">
    <c:if test="${not empty mensagemErro}">
        <div class="alert alert-danger" role="alert">
                ${mensagemErro}
        </div>
    </c:if>
</div>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        var today = new Date().toISOString().split('T')[0];
        document.getElementById("dataAgenda").value = today;
    });
</script>
</body>
</html>
