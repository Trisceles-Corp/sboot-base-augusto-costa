<%--
  Created by IntelliJ IDEA.
  User: Alexander Andrade
  Date: 22/05/2024
  Time: 09:17
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Augusto Costa</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
<div id="childPage">
    <div>
        <div class="headerContainer">
            <h4 class="headerTitle">Bloqueios</h4>
            <div class="headerRequired">* campos obrigatórios</div>
        </div>
        <div class="row p-3" id="linha-botao-cadastro">
            <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2" id="btn-cadastrar" onclick="toggleFormCadastro()">Cadastrar</button>
        </div>

        <!-- formulário de cadastro -->
        <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="tblBloqueio" action="${pageContext.request.contextPath}/bloqueio/salvar" method="POST">
            <form:hidden path="id" id="field_Id"/>
            <input type="hidden" name="userId" value="${userId}" />
            <div class="row">
                <div class="form-group col-md-4">
                    <form:label path="criadoPor" class="form-label" for="field_ColaboradorId">Colaborador:<span class="text-danger">*</span></form:label>
                    <form:select path="criadoPor" class="form-control" id="field_ColaboradorId" required="required">
                        <form:option value="" label=" Selecione "/>
                        <form:options items="${listarColaboradores}" itemValue="usuarioId" itemLabel="nomeCompleto"/>
                    </form:select>
                </div>
                <div class="form-group col-md-2">
                    <form:label path="dataBloqueio" class="form-label" for="field_DataBloqueio">Data:<span class="text-danger">*</span></form:label>
                    <form:input path="dataBloqueio" type="date" class="form-control" id="field_DataBloqueio" onchange="ajustaDiasDaSemana()" required="required" />
                </div>
                <div class="form-group col-md-2">
                    <form:label path="diasSemana" class="form-label" for="field_DiasSemanaId">Dia da Semana:<span class="text-danger">*</span></form:label>
                    <form:select path="diasSemana" class="form-control" id="field_DiasSemanaId" required="required" readonly="readonly" >
                        <form:option value="" label=" Selecione "/>
                        <form:options items="${listarDiasSemana}" itemValue="id" itemLabel="diasSemana"/>
                    </form:select>
                </div>
                <div class="form-group col-md-2">
                    <form:label path="periodo" class="form-label" for="field_PeriodoId">Periodo:<span class="text-danger">*</span></form:label>
                    <form:select path="periodo" class="form-control" id="field_PeriodoId" onchange="ajustaHorarioPeriodo()" required="required">
                        <form:option value="" label=" Selecione "/>
                        <form:options items="${listarPeriodos}" itemValue="id" itemLabel="nome"/>
                    </form:select>
                </div>
                <div class="form-group col-md-1">
                    <form:label path="horaInicial" class="form-label" for="field_HoraInicial">Início:<span class="text-danger">*</span></form:label>
                    <form:select path="horaInicial" class="form-control" id="field_HoraInicial" required="required" readonly="readonly">
                        <form:option value="" label=" Selecione "/>
                        <form:options items="${listarHorarios}" itemValue="horario" itemLabel="horario"/>
                    </form:select>
                </div>
                <div class="form-group col-md-1">
                    <form:label path="horaFinal" class="form-label" for="field_HoraFinal">Fim:<span class="text-danger">*</span></form:label>
                    <form:select path="horaFinal" class="form-control" id="field_HoraFinal" required="required" readonly="readonly">
                        <form:option value="" label=" Selecione "/>
                        <form:options items="${listarHorarios}" itemValue="horario" itemLabel="horario"/>
                    </form:select>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-12">
                    <form:label path="motivoBloqueio" class="form-label" for="field_MotivoBloqueio">Motivo Bloqueio:<span class="text-danger">*</span></form:label>
                    <form:textarea path="motivoBloqueio" class="form-control" rows="3" id="field_MotivoBloqueio" required="true" />
                </div>
            </div>
            <div class="mt-2">
                <button type="submit" class="btn btn-primary">Salvar</button>
                <button type="button" class="btn btn-danger m-1" id="cancelar-cadastro" onclick="toggleCloseCadastro()">Cancelar</button>
            </div>
        </form:form>
    </div>

    <div>
        <table id="tabelaDados" class="table table-bordered table-hover table-responsive my-3" >
            <thead class="table-dark">
            <tr class="gridHeader">
                <th scope="col" class="th-editar">Ações</th>
                <th scope="col">Data:</th>
                <th scope="col">Periodo:</th>
                <th scope="col">Dia Semana:</th>
                <th scope="col">Hora Inicial:</th>
                <th scope="col">hora Final:</th>
                <th scope="col">Motivo:</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bloqueio" items="${listarBloqueios}">
                <tr>
                    <td class="cel-img-tabela-clientes">
                        <form action="${pageContext.request.contextPath}/bloqueio/delete/${bloqueio.id}" method="POST">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarBloqueio('${bloqueio.id}', '${bloqueio.criadoPor}','${bloqueio.dataBloqueio}', '${bloqueio.periodo.id}', '${bloqueio.diasSemana.id}', '${bloqueio.horaInicial}', '${bloqueio.horaFinal}', '${bloqueio.motivoBloqueio}'); return false;" title="Editar">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/bloqueio/delete/${bloqueio.id}')">
                                <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                            </a>
                        </form>
                    </td>
                    <td><c:out value="${bloqueio.dataBloqueio}" /></td>
                    <td><c:out value="${bloqueio.periodo.nome}" /></td>
                    <td><c:out value="${bloqueio.diasSemana.diasSemana}" /></td>
                    <td><c:out value="${bloqueio.horaInicial}" /></td>
                    <td><c:out value="${bloqueio.horaFinal}" /></td>
                    <td><c:out value="${bloqueio.motivoBloqueio}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        const userId = document.querySelector('input[name="userId"]').value;
        const colaboradores = Array.from(document.querySelectorAll('#field_ColaboradorId option'));
        const colaboradorOption = colaboradores.find(option => option.value === userId);
        if (colaboradorOption) {
            document.getElementById('field_ColaboradorId').value = userId;
        }

        // AJAX para enviar o formulário e atualizar a div
        $('#form-cadastro').on('submit', function(event) {
            event.preventDefault(); // Impede o envio normal do formulário
            const form = $(this);
            $.ajax({
                type: form.attr('method'),
                url: form.attr('action'),
                data: form.serialize(),
                success: function(response) {
                    $('#childPage').html(response); // Atualiza a div com a resposta do servidor
                },
                error: function(xhr, status, error) {
                    console.error('Erro ao salvar:', error);
                }
            });
        });
    });
</script>
</body>
</html>
