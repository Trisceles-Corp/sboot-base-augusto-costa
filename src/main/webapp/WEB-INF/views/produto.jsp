<%--
  Created by IntelliJ IDEA.
  User: ck_aa
  Date: 11/03/2024
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

    <script>
        function verificarNomeAntesDeSalvar() {
            const nomeInput = document.getElementById('field_Name');
            const nome = nomeInput.value.trim();

            if (nome === '') {
                alert('O campo Nome é obrigatório e não pode estar vazio.');
                return false;
            }
            return true;
        }
    </script>

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="itemHeader">
                <h3>Configuração - <small>Produto</small></h3>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <form:form class="bootstrap-form-with-validation" id="produtoForm" modelAttribute="tblProduto" action="${pageContext.request.contextPath}/produto/salvar" method="POST" onsubmit="return verificarNomeAntesDeSalvar()">
                <form:hidden path="id" id="field_Id"/>
                <div class="form-group mb-3">
                    <form:label path="codigoInterno" class="form-label" for="text-input">Codigo Interno:</form:label>
                    <form:input path="codigoInterno" class="form-control" type="text" id="field_CodigoInterno" />
                </div>

                <div class="form-group mb-3">
                    <form:label path="descricaoProduto" class="form-label" for="text-input">Nome:</form:label>
                    <form:input path="descricaoProduto" class="form-control" type="text" id="field_Name" />
                </div>

                <div class="form-group mb-3">
                    <form:label path="codigoBarras" class="form-label" for="text-input">Codigo de Barras:</form:label>
                    <form:input path="codigoBarras" class="form-control" type="text" id="field_CodigoBarras" />
                </div>

                <div class="form-group mb-3">
                    <form:label path="marca" class="form-label">Marca:</form:label>
                    <form:select path="marca" class="form-control" id="field_MarcaId">
                        <form:option value="" label=" Selecione "/>
                        <form:options items="${listaMarcas}" itemValue="id" itemLabel="descricaoMarca"/>
                    </form:select>
                </div>

                <div class="form-group mb-3">
                    <form:label path="linha" class="form-label">Linha:</form:label>
                    <form:select path="linha" class="form-control" id="field_LinhaId">
                        <form:option value="" label=" Selecione "/>
                        <form:options items="${listaLinhas}" itemValue="id" itemLabel="descricaoLinha"/>
                    </form:select>
                </div>

                <div class="form-group mb-3">
                    <form:label path="caracteristica" class="form-label">Característica:</form:label>
                    <form:select path="caracteristica" class="form-control" id="field_CaracteristicaId">
                        <form:option value="" label=" Selecione "/>
                        <form:options items="${listaCaracteristicas}" itemValue="id" itemLabel="descricaoCaracteristica"/>
                    </form:select>
                </div>

                <div class="form-group mb-3">
                    <form:label path="estoqueMinimo" class="form-label" for="text-input">Estoque Mínimo:</form:label>
                    <form:input path="estoqueMinimo" class="form-control" type="text" id="field_EstoqueMinimo" />
                </div>

                <div class="form-group mb-3">
                    <form:label path="custo" class="form-label" for="text-input">Custo:</form:label>
                    <form:input path="custo" class="form-control" type="text" id="field_Custo" />
                </div>

                <div class="form-group mb-3">
                    <form:label path="valorVenda" class="form-label" for="text-input">Valor Venda:</form:label>
                    <form:input path="valorVenda" class="form-control" type="text" id="field_valorVenda" />
                </div>

                <div class="form-group mb-3">
                    <form:label path="comissao" class="form-label" for="text-input">Comissão:</form:label>
                    <form:input path="comissao" class="form-control" type="text" id="field_Comissao" />
                </div>

                <div class="form-group mb-3">
                    <div class="form-check">
                        <form:checkbox path="ativo" id="field_Active"/>
                        <form:label path="ativo" class="form-check-label" for="field_Active">Ativo</form:label>
                    </div>
                </div>
                <div class="form-group mb-3">
                    <input type="submit" class="btn btn-primary" id="btnSalvar" value="Salvar" />
                </div>
            </form:form>
        </div>
    </div>
</div>

<div class="table-responsive">
    <table class="table">
        <thead>
        <tr class="gridHeader">
            <th>Ações</th>
            <th>Id</th>
            <th>Codigo Interno</th>
            <th>Nome</th>
            <th>Codigo de Barras</th>
            <th>Marca</th>
            <th>Linha</th>
            <th>Característica</th>
            <th>Estoque Mínimo</th>
            <th>Custo</th>
            <th>Valor Venda</th>
            <th>Comissão</th>
            <th>Ativo</th>
            <th>Data Alteração</th>
            <th>AlteradoPor</th>
        </tr>
        </thead>
        <c:forEach var="produto" items="${listaProdutos}">
            <tbody>
            <tr>
                <td>
                    <a href="#" class="btn-visualizar" onclick="visualizarProduto('${produto.id}', '${produto.codigoInterno}', '${produto.descricaoProduto}', '${produto.codigoBarras}', '${produto.marca.id}', '${produto.linha.id}', '${produto.caracteristica.id}', '${produto.estoqueMinimo}', '${produto.custo}', '${produto.valorVenda}', '${produto.comissao}', '${produto.ativo}'); return false;" title="Visualizar">
                    </a>
                </td>
                <td><c:out value="${produto.id}" /></td>
                <td><c:out value="${produto.codigoInterno}" /></td>
                <td><c:out value="${produto.descricaoProduto}" /></td>
                <td><c:out value="${produto.codigoBarras}" /></td>
                <td><c:out value="${produto.marca.descricaoMarca}" /></td>
                <td><c:out value="${produto.linha.descricaoLinha}" /></td>
                <td><c:out value="${produto.caracteristica.descricaoCaracteristica}" /></td>
                <td><c:out value="${produto.estoqueMinimo}" /></td>
                <td><c:out value="${produto.custo}" /></td>
                <td><c:out value="${produto.valorVenda}" /></td>
                <td><c:out value="${produto.comissao}" /></td>
                <td><c:out value="${produto.ativo ? 'Sim' : 'Não'}" /></td>
                <td><c:out value="${produto.dataAlteracao}" /></td>
                <td><c:out value="${produto.alteradoPor.nome}" /></td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
</body>
</html>
