<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Características</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
</head>
<body>
    <div>
        <div class="row" id="linha-botao-cadastro">
            <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2 " id="btn-cadastrar" onclick="toggleFormCadastro()">Cadastrar característica</button>
        </div>

        <!-- formulário de cadastro -->
        <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="tblCaracteristica" action="${pageContext.request.contextPath}/caracteristica/salvar" method="POST">
            <form:hidden path="id" id="field_Id"/>
            <div class="row">
                <div class="form-group col-md-6">
                    <form:label path="descricaoCaracteristica">Descrição:</form:label>
                    <form:input path="descricaoCaracteristica" type="text" class="form-control" id="field_Descricao" maxlength="100"/>
                </div>
            </div>
            <div class="mt-2">
                <button type="submit" class="btn btn-primary">Salvar</button>
                <button type="button" class="btn btn-danger m-1" id="cancelar-cadastro">Cancelar</button>
            </div>
        </form:form>
        <script>
            /* abrir e fechar formulario */
            const formfornecedorCadast = document.getElementById("form-cadastro");
            const btnCancelarCadast = document.getElementById("cancelar-cadastro")

            btnCancelarCadast.addEventListener("click", () => {
                formfornecedorCadast.style.display = "none";
            });
        </script>

        <!-- tabela mostrando os cadastrados -->
        <table id="tabelaCaracteristicas" class="table table-bordered table-hover table-responsive my-3" >
            <thead class="table-dark">
            <tr class="gridHeader">
                <th scope="col" class="th-editar">Ações</th>
                <th scope="col">Descrição</th>
                <th scope="col">Data de Criação</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="caracteristica" items="${listaCaracteristica}">
                <tr>
                    <td class="cel-img-tabela-clientes">
                        <form action="${pageContext.request.contextPath}/caracteristica/delete/${caracteristica.id}" method="POST">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarCaracteristica('${caracteristica.id}', '${caracteristica.descricaoCaracteristica}'); return false;" title="Editar">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/caracteristica/delete/${caracteristica.id}')">
                                <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                            </a>
                        </form>
                    </td>
                    <td><c:out value="${caracteristica.descricaoCaracteristica}" /></td>
                    <td><c:out value="${caracteristica.dataCriacao}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
