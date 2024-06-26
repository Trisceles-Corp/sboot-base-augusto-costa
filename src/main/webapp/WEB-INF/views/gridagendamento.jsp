<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.URLDecoder" %><%--
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
    <title>Augusto Costa</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div class="row mx-0 my-2" id="dvHeader" >
    <input type="date" class="input-date-agenda col-md-2 p-1" id="dataAgenda" onchange="atualizarGridAgendamentos(contextPath + '/gridagendamento', this.value)" placeholder="mm-dd-yyyy" >
    <div class="col-md-10">
        <div class="botoes-agenda">
            <button type="button" class="btn btn-outline-primary" onclick="carregarConteudo(contextPath + '/agendamento')" id="btn-agendar">Agendar cliente</button>
            <button type="button" class="btn btn-outline-secondary" onclick="carregarConteudo(contextPath + '/bloqueio')" id="btn-bloqueio" style="display: <%= perfil.equals("4") ? "none" : "inline-block" %>">Bloquear horário</button>
        </div>
    </div>
</div>
<div class="row" id="dvGrid">
    <div class="panelGrid col-md-2" id="panel-pesquisa">
        <div class="form-group col-md-12">
            <form class="form-group col-md-12" name="theForm">
                <div class="form-group col-md-12" style="display: none">
                    <input class="form-check-input" type="checkbox" id="barbeiros" name="theGroup"  value="option2" onClick="clearGroup(this);">
                    <span style="padding: 10px">Barbeiros</span>
                </div>
                <div class="form-group col-md-12" style="display: none">
                    <input class="form-check-input" type="checkbox" id="cabeleireiros" name="theGroup" value="option2" onClick="clearGroup(this);">
                    <span style="padding: 10px">Cabeleireiros</span>
                </div>
                <div class="form-group col-md-12" style="display: none">
                    <input class="form-check-input" type="checkbox" id="manicures" name="theGroup" value="option2" onClick="clearGroup(this);">
                    <span style="padding: 10px">Manicures</span>
                </div>
                <div class="form-group col-md-12">
                    <input class="form-check-input" type="checkbox" id="Todos" name="theGroup" value="option2" checked="checked" onClick="clearGroup(this);">
                    <span style="padding: 10px">Todos</span>
                </div>
            </form>
        </div>
        <div>
            <hr>
        </div>
        <div class="form-group col-md-12">
            <div class="color-box green"></div>
            <span style="padding: 10px">Agendada</span>
        </div>
        <div class="form-group col-md-12">
            <div class="color-box red"></div>
            <span style="padding: 10px">Aberta</span>
        </div>
        <div class="form-group col-md-12">
            <div class="color-box orange"></div>
            <span style="padding: 10px">Em espera</span>
        </div>
        <div class="form-group col-md-12">
            <div class="color-box blue"></div>
            <span style="padding: 10px">Finalizada</span>
        </div>
    </div>
    <div class="panelGrid col-md-10" id="panel-resultado">
        <div class="row">
            <table id="tabelaGridAgendamento" class="table table-bordered table-hover table-responsive my-3">
                <thead class="table-secondary">
                <tr class="gridHeader">
                    <th scope="col" class="th-editar">Horário</th>
                    <c:forEach var="colaborador" items="${agendamentosPorColaborador.keySet()}">
                        <th scope="col">${colaborador}</th>
                    </c:forEach>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="hora" begin="9" end="18">
                    <tr>
                        <td>${hora}:00</td>
                        <c:forEach var="colaborador" items="${agendamentosPorColaborador.keySet()}">
                            <td>
                                <c:forEach var="agendamento" items="${agendamentosPorColaborador.get(colaborador)}">
                                    <c:if test="${agendamento.horarioIncial.hour == hora || (agendamento.horarioIncial.hour < hora && agendamento.horarioFinal.hour > hora)}">
                                        <div class="agendamento">
                                            <c:choose>
                                                <c:when test="${agendamento.situacao != 'Bloqueio'}">
                                                    <a href="#" onclick="carregarConteudo(contextPath + '/agendamento/${agendamento.agendamento.id}')">
                                                        <span class="situacao-${agendamento.situacao}">
                                                            ${agendamento.agendamento.cliente.nome}<br>
                                                            ${agendamento.servico.nome}
                                                        </span>
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="situacao-${agendamento.situacao}">
                                                        ${agendamento.agendamento.cliente.nome}<br>
                                                        ${agendamento.servico.nome}
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>
                        <td>${hora}:30</td>
                        <c:forEach var="colaborador" items="${agendamentosPorColaborador.keySet()}">
                            <td>
                                <c:forEach var="agendamento" items="${agendamentosPorColaborador.get(colaborador)}">
                                    <c:if test="${agendamento.horarioIncial.hour == hora && agendamento.horarioIncial.minute != 30 || (agendamento.horarioIncial.hour < hora && agendamento.horarioFinal.hour > hora) || (agendamento.horarioIncial.hour == hora && agendamento.horarioFinal.minute > 30)}">
                                        <div class="agendamento">
                                            <c:choose>
                                                <c:when test="${agendamento.situacao != 'Bloqueio'}">
                                                    <a href="#" onclick="carregarConteudo(contextPath + '/agendamento/${agendamento.agendamento.id}')">
                                                        <span class="situacao-${agendamento.situacao}">
                                                            ${agendamento.agendamento.cliente.nome}<br>
                                                            ${agendamento.servico.nome}
                                                        </span>
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="situacao-${agendamento.situacao}">
                                                        ${agendamento.agendamento.cliente.nome}<br>
                                                        ${agendamento.servico.nome}
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        let today = new Date().toISOString().split('T')[0];
        document.getElementById("dataAgenda").value = formatDateForInput(today);
    });
</script>
</body>
</html>
