<%--
  Created by IntelliJ IDEA.
  User: Alexander Andrade
  Date: 02/04/2024
  Time: 09:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pt-br">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Augusto Costa</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/icons/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" >
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/theme.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>

    <!-- Mantenha apenas uma versão do jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>

    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
</head>

<body>
<div class="full">
    <div class="header" id="div_header">
        <div class="welcome mx-5 p-2">
            <p>Augusto Costa Spa</p>
        </div>
        <!-- <div class="login"> -->
        <!-- <img src="${pageContext.request.contextPath}/img/icons login/user-999.png" alt=""> -->
        <!-- </div> -->

        <ul class="navbar-nav flex-nowrap ms-auto">
            <li class="nav-item dropdown d-sm-none no-arrow"><a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#"><i class="fas fa-search"></i></a>
                <div class="dropdown-menu dropdown-menu-end p-3 animated--grow-in" aria-labelledby="searchDropdown">
                    <form class="me-auto navbar-search w-100">
                        <div class="input-group"><input class="bg-light form-control border-0 small" type="text" placeholder="Search for ...">
                            <div class="input-group-append"><button class="btn btn-primary py-0" type="button"><i class="fas fa-search"></i></button></div>
                        </div>
                    </form>
                </div>
            </li>
            <div class="d-none d-sm-block topbar-divider"></div>
            <li class="nav-item dropdown no-arrow">
                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#"><span class="d-none d-lg-inline me-2 text-gray-600 small">Alexander Andrade</span><img class="border rounded-circle img-profile" src="${pageContext.request.contextPath}/img/avatars/avatar3.jpeg"></a>
                    <div class="dropdown-menu shadow dropdown-menu-end animated--grow-in"><a class="dropdown-item" href="#"><i class="fas fa-user fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Profile</a><a class="dropdown-item" href="#"><i class="fas fa-cogs fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Settings</a><a class="dropdown-item" href="#"><i class="fas fa-list fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Activity log</a>
                        <div class="dropdown-divider"></div><a class="dropdown-item" href="#"><i class="fas fa-sign-out-alt fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Logout</a>
                    </div>
                </div>
            </li>
            <div class="d-none d-sm-block topbar-divider"></div>
        </ul>
    </div>
    <div class="sidenav">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/logos/image(5).png" alt="">
        </div>
        <div class="nav-pages">
            <a href="agenda.html" class="nav-option" id="agenda">
                <div class="contain-option w-100">
                    <div class="my-2">
                        <img src="${pageContext.request.contextPath}/img/icon agenda/agenda-999.png" id="agenda-img">
                        <span class="mx-2">Agenda</span>
                    </div>
                    <script>
                        /* mudar cor da imagem ao passar o mouse em cima*/
                        const agenda = document.getElementById("agenda");
                        const imgAgenda = agenda.querySelector("img");

                        agenda.addEventListener("mouseover", () => {
                            imgAgenda.setAttribute("src", "${pageContext.request.contextPath}/img/icon agenda/agenda-F0DD6C.png");
                        });

                        agenda.addEventListener("mouseout", () => {
                            imgAgenda.setAttribute("src", "${pageContext.request.contextPath}/img/icon agenda/agenda-999.png");
                        });
                    </script>
                </div>
            </a>
            <a class="nav-option" id="clientes">
                <div class="contain-option w-100">
                    <div class="my-2">
                        <img src="${pageContext.request.contextPath}/img/icon clientes/cliente-999.png" id="cliente-img">
                        <button class="mx-2" onclick="carregarConteudo(contextPath + '/cliente')" >Clientes</button>
                    </div>
                    <script>
                        /* mudar cor da imagem ao passar o mouse em cima*/

                        const cliente = document.getElementById("clientes");
                        const imgCliente = cliente.querySelector("img");

                        cliente.addEventListener("mouseover", () => {
                            imgCliente.setAttribute("src", "${pageContext.request.contextPath}/img/icon clientes/cliente-F0DD6C.png");
                        });

                        cliente.addEventListener("mouseout", () => {
                            imgCliente.setAttribute("src", "${pageContext.request.contextPath}/img/icon clientes/cliente-999.png");
                        });
                    </script>
                </div>
            </a>
            <a class="nav-option" id="estoque">
                <div class="contain-option w-100">
                    <div class="my-2">
                        <img src="${pageContext.request.contextPath}/img/icon estoque/estoque-999.png" id="estoque-img">
                        <span class="mx-2">Estoque</span>
                    </div>
                    <div class="contain-sub-option">
                        <div class="sub-options px-5 py-1">
                            <button class="contain-option-button-inactive" onclick="carregarConteudo(contextPath + '/produto')" >Produtos</button><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <button class="contain-option-button-inactive" onclick="carregarConteudo(contextPath + '/fornecedor')" >Fornecedores</button><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <span class="sub-option-item" onclick="carregarConteudo(contextPath + '/pedidosCompra')">Pedidos de compra</span><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <span class="sub-option-item" onclick="carregarConteudo(contextPath + '/inventario')">Inventário</span><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <span class="sub-option-item">Solicitação de saída</span><br>
                        </div>
                    </div>
                    <script>
                        /* mudar cor da imagem ao passar o mouse em cima*/
                        const estoque = document.getElementById("estoque");
                        const imgEstoque = estoque.querySelector("img");
                        const subOptionsEstoque = estoque.querySelectorAll(".sub-options");

                        estoque.addEventListener("mouseover", () => {
                            imgEstoque.setAttribute("src", "${pageContext.request.contextPath}/img/icon estoque/estoque-F0DD6C.png");
                            for(var i = 0; i < subOptionsEstoque.length; i++){
                                subOptionsEstoque[i].style.display = "block";
                            }
                        });

                        estoque.addEventListener("mouseout", () => {
                            imgEstoque.setAttribute("src", "${pageContext.request.contextPath}/img/icon estoque/estoque-999.png");
                            for(var i = 0; i < subOptionsEstoque.length; i++){
                                subOptionsEstoque[i].style.display = "none";
                            }
                        });

                    </script>
                </div>
            </a>
            <a href="financeiro.html" class="nav-option" id="financeiro">
                <div class="contain-option w-100">
                    <div class="my-2">
                        <img src="${pageContext.request.contextPath}/img/icon financeiro/financeiro-999.png" id="financeiro-img">
                        <span class="mx-2">Financeiro</span>
                    </div>
                    <div class="contain-sub-option">
                        <div class="sub-options px-5 py-1">
                            <span class="sub-option-item">Caixas em aberto</span><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <span class="sub-option-item">Comandas abertas</span><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <span class="sub-option-item">Comissões</span><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <span class="sub-option-item">Entradas e saídas</span><br>
                        </div>
                    </div>
                    <script>
                        /* mudar cor da imagem ao passar o mouse em cima*/
                        const financeiro = document.getElementById("financeiro");
                        const imgFinanceiro = financeiro.querySelector("img");
                        const subOptionsFinanceiro = financeiro.querySelectorAll(".sub-options");

                        financeiro.addEventListener("mouseover", () => {
                            imgFinanceiro.setAttribute("src", "${pageContext.request.contextPath}/img/icon financeiro/financeiro-F0DD6C.png");
                            for(var i = 0; i < subOptionsFinanceiro.length; i++){
                                subOptionsFinanceiro[i].style.display = "block";
                            }
                        });

                        financeiro.addEventListener("mouseout", () => {
                            imgFinanceiro.setAttribute("src", "${pageContext.request.contextPath}/img/icon financeiro/financeiro-999.png");
                            for(var i = 0; i < subOptionsFinanceiro.length; i++){
                                subOptionsFinanceiro[i].style.display = "none";
                            }
                        });
                    </script>
                </div>
            </a>
            <a href="agenda.html" class="nav-option" id="dashboard">
                <div class="contain-option w-100">
                    <div class="my-2">
                        <img src="${pageContext.request.contextPath}/img/icon agenda/agenda-999.png" id="dashboard-img">
                        <span class="mx-2">Dashboard</span>
                    </div>
                    <script>
                        /* mudar cor da imagem ao passar o mouse em cima*/
                        const dashboard = document.getElementById("dashboard");
                        const imgDashboard = dashboard.querySelector("img");

                        dashboard.addEventListener("mouseover", () => {
                            imgDashboard.setAttribute("src", "${pageContext.request.contextPath}/img/icon agenda/agenda-F0DD6C.png");
                        });

                        dashboard.addEventListener("mouseout", () => {
                            imgDashboard.setAttribute("src", "${pageContext.request.contextPath}/img/icon agenda/agenda-999.png");
                        });
                    </script>
                </div>
            </a>
            <a href="agenda.html" class="nav-option" id="relatorios">
                <div class="contain-option w-100">
                    <div class="my-2">
                        <img src="${pageContext.request.contextPath}/img/icon agenda/agenda-999.png" id="relatorios-img">
                        <span class="mx-2">Relatórios</span>
                    </div>
                    <script>
                        /* mudar cor da imagem ao passar o mouse em cima*/
                        const relatorios = document.getElementById("relatorios");
                        const imgRelatorios = relatorios.querySelector("img");

                        relatorios.addEventListener("mouseover", () => {
                            imgRelatorios.setAttribute("src", "${pageContext.request.contextPath}/img/icon agenda/agenda-F0DD6C.png");
                        });

                        relatorios.addEventListener("mouseout", () => {
                            imgRelatorios.setAttribute("src", "${pageContext.request.contextPath}/img/icon agenda/agenda-999.png");
                        });
                    </script>
                </div>
            </a>
            <a class="nav-option" id="config">
                <div class="contain-option w-100">
                    <div class="my-2">
                        <img src="${pageContext.request.contextPath}/img/icon estoque/estoque-999.png" id="config-img">
                        <span class="mx-2">Configurações</span>
                    </div>
                    <div class="contain-sub-option">
                        <div class="sub-options px-5 py-1">
                            <button class="contain-option-button-inactive" onclick="carregarConteudo(contextPath + '/caracteristica')" >Característica</button><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <button class="contain-option-button-inactive" onclick="carregarConteudo(contextPath + '/cargo')" >Cargo</button><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <button class="contain-option-button-inactive" onclick="carregarConteudo(contextPath + '/categoria')" >Categoria</button><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <button class="contain-option-button-inactive" onclick="carregarConteudo(contextPath + '/linha')" >Linha</button><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <button class="contain-option-button-inactive" onclick="carregarConteudo(contextPath + '/localestoque')" >Local Estoque</button><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <button class="contain-option-button-inactive" onclick="carregarConteudo(contextPath + '/marca')" >Marca</button><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <button class="contain-option-button-inactive" onclick="carregarConteudo(contextPath + '/perfil')" >Perfil</button><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <button class="contain-option-button-inactive" onclick="carregarConteudo(contextPath + '/servico')" >Serviços</button><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <button class="contain-option-button-inactive" onclick="carregarConteudo(contextPath + '/situacaoagendamento')" >Situação</button><br>
                        </div>
                    </div>
                    <script>
                        /* mudar cor da imagem ao passar o mouse em cima*/
                        const config = document.getElementById("config");
                        const imgConfig = config.querySelector("img");
                        const subOptionsConfig = config.querySelectorAll(".sub-options");

                        config.addEventListener("mouseover", () => {
                            imgConfig.setAttribute("src", "${pageContext.request.contextPath}/img/icon estoque/estoque-F0DD6C.png");
                            for(var i = 0; i < subOptionsConfig.length; i++){
                                subOptionsConfig[i].style.display = "block";
                            }
                        });

                        config.addEventListener("mouseout", () => {
                            imgConfig.setAttribute("src", "${pageContext.request.contextPath}/img/icon estoque/estoque-999.png");
                            for(var i = 0; i < subOptionsConfig.length; i++){
                                subOptionsConfig[i].style.display = "none";
                            }
                        });
                    </script>
                </div>
            </a>
        </div>
    </div>
    <div class="content-main p-5" id="mainContent">

    </div>
    <footer class="footer">
        <p> Copyright @ Trísceles Corp 2024 </p>
    </footer>
</div>
</body>
</html>