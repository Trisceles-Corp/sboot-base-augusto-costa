<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Agendamento</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap DatePicker CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <!-- FullCalendar -->
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.css' rel='stylesheet' />
    <link href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.print.min.css' rel='stylesheet' media='print' />
    <style>
        .color-box { width: 10px; height: 10px; display: inline-block; }
        .blue { background-color: blue; }
        .green { background-color: green; }
        .red { background-color: red; }
        .yellow { background-color: yellow; }
    </style>
</head>
<body>
<div class="container">
    <div class="row mt-3">
        <div class="col-md-4">
            <!-- Calendário -->
            <div id="calendar"></div>
            <div class="form-group">
                <div class="input-group date" id="datepicker">
                    <input type="text" class="form-control">
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-th"></span>
                    </div>
                </div>
            </div>

            <!-- Radio Buttons -->
            <div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="profissional" id="barbeiros" value="barbeiros">
                    <label class="form-check-label" for="barbeiros">Barbeiros</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="profissional" id="cabeleireiros" value="cabeleireiros">
                    <label class="form-check-label" for="cabeleireiros">Cabeleireiros</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="profissional" id="depiladoras" value="depiladoras">
                    <label class="form-check-label" for="depiladoras">Depiladoras</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="profissional" id="manicures" value="manicures">
                    <label class="form-check-label" for="manicures">Manicures</label>
                </div>
            </div>

            <!-- Labels Informativos -->
            <div class="mt-3">
                <div><span class="color-box blue"></span> <label>Finalizado</label></div>
                <div><span class="color-box green"></span> <label>Agendado</label></div>
                <div><span class="color-box red"></span> <label>Em atendimento</label></div>
                <div><span class="color-box yellow"></span> <label>Em espera</label></div>
            </div>
        </div>

        <div class="col-md-8 text-right">
            <!-- Botões de Comando -->
            <button type="button" class="btn btn-primary">Agendar</button>
            <button type="button" class="btn btn-secondary">Bloquear</button>
        </div>
    </div>

    <!-- Grid Central com JSTL -->
    <div class="row mt-3">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th>Horário</th>
                    <th>Alexander</th>
                    <th>Paulo</th>
                    <th>Monica</th>
                    <th>Maryene</th>
                    <th>Vitor</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="i" begin="9" end="18" step="1">
                    <tr>
                        <td>${i < 10 ? '0' : ''}${i}:00</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <c:if test="${i != 18}">
                        <tr>
                            <td>${i < 10 ? '0' : ''}${i}:30</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Bootstrap e DatePicker JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script>
    $(document).ready(function() {
        $('#datepicker').datepicker({
            format: "dd/mm/yyyy",
            todayHighlight: true,
            autoclose: true,
            language: "pt-BR"
        }).datepicker("setDate", new Date());
    });
</script>
<!-- FullCalendar -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/locale/pt-br.js'></script>
<script>
    $(document).ready(function() {
        $('#calendar').fullCalendar({
            locale: 'pt-br',
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            defaultDate: new Date(),
            navLinks: false, // can click day/week names to navigate views
            editable: false,
            eventLimit: true, // allow "more" link when too many events
            events: [
                // Aqui você pode adicionar eventos dinamicamente
            ]
        });
    });
</script>
</body>
</html>