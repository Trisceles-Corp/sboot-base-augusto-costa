<%--
  Created by IntelliJ IDEA.
  User: ck_aa
  Date: 15/04/2024
  Time: 16:10
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
    <div class="itemHeader">
        <h4>Inventário Estoque</h4>
    </div>
</div>
<div>
    <table id="tabelaDados" class="table table-bordered table-hover table-responsive my-3">
        <thead class="table-dark">
        <tr class="gridHeader">
            <th scope="col">Codigo Interno</th>
            <th scope="col">Local Estoque</th>
            <th scope="col">Produto</th>
            <th scope="col">Quantidade</th>
            <th scope="col">Valor</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="estoque" items="${listarEstoque}">
            <tr>
                <th scope="row"><c:out value="${estoque.codigoInterno}" /></th>
                <td><c:out value="${estoque.descricaoLocal}" /></td>
                <td><c:out value="${estoque.descricaoProduto}" /></td>
                <td><c:out value="${estoque.qtdProduto}" /></td>
                <td><c:out value="${estoque.valorProduto}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
