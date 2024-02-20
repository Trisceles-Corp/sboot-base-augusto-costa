<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Serviços</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <script type="text/javascript">
        function visualizarServico(servicoId, nome, tempo, valor, desconto, comissao, observacao, ativo) {
            document.querySelector("input[name='tblServico.id']").value = servicoId;
            document.querySelector("textarea[name='tblServico.nome']").value = nome;
            document.querySelector("textarea[name='tblServico.tempo']").value = tempo;
            document.querySelector("textarea[name='tblServico.valor']").value = valor;
            document.querySelector("textarea[name='tblServico.desconto']").value = desconto;
            document.querySelector("textarea[name='tblServico.comissao']").value = comissao;
            document.querySelector("textarea[name='tblServico.observacao']").value = observacao;
            document.querySelector("input[name='tblServico.ativo']").checked = ativo === 'true';
        }
    </script>
</head>
<body>

<form:form id="tipoForm" modelAttribute="tblServico" action="${pageContext.request.contextPath}/servico/salvar" method="POST">
    <form:hidden path="id" />
    <table>
        <tr>
            <td><form:label path="nome">Nome:</form:label></td>
            <td><form:input path="nome" /></td>
            <td><form:label path="tempo">Tempo:</form:label></td>
            <td><form:input path="tempo" /></td>
            <td><form:label path="valor">Valor:</form:label></td>
            <td><form:input path="valor" /></td>
            <td><form:label path="desconto">Desconto:</form:label></td>
            <td><form:input path="desconto" /></td>
            <td><form:label path="comissao">Comissão:</form:label></td>
            <td><form:input path="comissao" /></td>
            <td><form:label path="observacao">Observacao:</form:label></td>
            <td><form:input path="observacao" /></td>

            <td><form:checkbox path="ativo" label="Ativo" /></td>
        </tr>
    </table>
    <div class="button-bar">
        <input type="submit" value="Salvar" />
        <a href="${pageContext.request.contextPath}/servico/novo">Novo</a>
    </div>
</form:form>

<br/>

<table>
    <tr>
        <th>Ações</th>
        <th>Id</th>
        <th>Nome</th>
        <th>Tempo</th>
        <th>Valor</th>
        <th>Desconto</th>
        <th>Comissao</th>
        <th>Ativo</th>
        <th>Data de Criação</th>
        <th>Criado Por</th>
    </tr>
    <c:forEach var="servico" items="${listaServicos}">
        <tr>
            <td>
                <a href="#" onclick="visualizarServico('${servico.id}', '${servico.nome}', '${servico.tempo}', '${servico.valor}', '${servico.desconto}', '${servico.comissao}', '${servico.observacao}', '${servico.ativo}'); return false;" title="Visualizar">
                    <img src="${pageContext.request.contextPath}/images/view.png" alt="Visualizar" />
                </a>
                <!-- Adicione mais ações aqui, como editar e excluir, conforme necessário -->
            </td>
            <td><c:out value="${servico.id}" /></td>
            <td><c:out value="${servico.nome}" /></td>
            <td><c:out value="${servico.tempo}" /></td>
            <td><c:out value="${servico.valor}" /></td>
            <td><c:out value="${servico.desconto}" /></td>
            <td><c:out value="${servico.comissao}" /></td>
            <td><c:out value="${servico.ativo ? 'Sim' : 'Não'}" /></td>
            <td><c:out value="${servico.dataCriacao}" /></td>
            <td><c:out value="${servico.criadoPor}" /></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
