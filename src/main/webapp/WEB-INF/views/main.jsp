<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Augusto Costa</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />

    <!-- Bootstrap DatePicker CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" />

    <!-- FullCalendar -->
    <link rel='stylesheet' type="text/css" href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.css' />
    <link rel='stylesheet' type="text/css" href='https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.print.min.css' media='print' />

    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form-styles.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/fontawesome-all.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/fontawesome5-overrides.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Navigation-Menu.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/theme.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>

    <script>
        var contextPath = "${pageContext.request.contextPath}";

        $(document).ready(function() {
            carregarConteudo("agendamento");
        });

        function carregarConteudo(nomePagina) {
            var url = contextPath + "/" + nomePagina;
            fetch(url)
                .then(response => response.text())
                .then(data => {
                    document.getElementById("conteudoPrincipal").innerHTML = data;
                })
                .catch(error => {
                    console.error("Erro ao carregar a página:", error);
                });
        }
    </script>

</head>

<body id="page-top">
<div id="wrapper">
    <div class="d-flex flex-column" id="content-wrapper">
        <div id="content">
            <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle me-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                    <form class="d-none d-sm-inline-block me-auto ms-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group"><input class="bg-light form-control border-0 small" type="text" placeholder="Search for ..."><button class="btn btn-primary py-0" type="button"><i class="fas fa-search"></i></button></div>
                    </form>
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
                            <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" aria-expanded="false" data-bs-toggle="dropdown" href="#"><span class="d-none d-lg-inline me-2 text-gray-600 small">Alexander Andrade</span></a>
                                <div class="dropdown-menu shadow dropdown-menu-end animated--grow-in"><a class="dropdown-item" href="#"><i class="fas fa-user fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Profile</a><a class="dropdown-item" href="#"><i class="fas fa-cogs fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Settings</a><a class="dropdown-item" href="#"><i class="fas fa-list fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Activity log</a>
                                    <div class="dropdown-divider"></div><a class="dropdown-item" href="#"><i class="fas fa-sign-out-alt fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Logout</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
            <nav class="navbar navbar-light navbar-expand-md">
                <div class="container-fluid">
                    <div><a class="navbar-brand d-none" href="#"> </a><button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1"><span class="visually-hidden">Toggle navigation</span><span class="navbar-toggler-icon"></span></button></div>
                    <div class="collapse navbar-collapse" id="navcol-1">
                        <ul class="navbar-nav main-nav">
                            <li class="nav-item"><a class="nav-link" id="agendamento" href="#" onclick="carregarConteudo('agendamento'); return false;">Agendamento</a></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="financeiroDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Financeiro</a>
                                <ul class="dropdown-menu" aria-labelledby="financeiroDropdown">
                                    <li><a class="dropdown-item" id="caixa" href="#" onclick="carregarConteudo('caixa'); return false;">Caixas Abertos</a></li>
                                    <li><a class="dropdown-item" id="comandas" href="#" onclick="carregarConteudo('comandas'); return false;">Comandas Abertos</a></li>
                                    <li><a class="dropdown-item" id="comissoes" href="#" onclick="carregarConteudo('comissoes'); return false;">Comissões</a></li>
                                    <li><a class="dropdown-item" id="entradasaidas" href="#" onclick="carregarConteudo('entradasaidas'); return false;">Entradas e Saídas</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="estoqueDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Estoque</a>
                                <ul class="dropdown-menu" aria-labelledby="estoqueDropdown">
                                    <li><a class="dropdown-item" id="produtos" href="#" onclick="carregarConteudo('produto'); return false;">Produtos</a></li>
                                    <li><a class="dropdown-item" id="forncecedores" href="#" onclick="carregarConteudo('forncecedores'); return false;">Fornecedores</a></li>
                                    <li><a class="dropdown-item" id="pedidocompras" href="#" onclick="carregarConteudo('pedidocompras'); return false;">Pedido de Compras</a></li>
                                    <li><a class="dropdown-item" id="inventario" href="#" onclick="carregarConteudo('inventario'); return false;">Inventário</a></li>
                                    <li><a class="dropdown-item" id="solicitacaosaidas" href="#" onclick="carregarConteudo('solicitacaosaidas'); return false;">Solicitação de Saídas</a></li>
                                </ul>
                            </li>
                            <li class="nav-item"><a class="nav-link" id="dashboard" href="#">Dashboard </a></li>
                            <li class="nav-item"><a class="nav-link" id="relatorios" href="#">Relatórios </a></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="cadastroDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Cadastro</a>
                                <ul class="dropdown-menu" aria-labelledby="cadastroDropdown">
                                    <li><a class="dropdown-item" id="caracteristica" href="#" onclick="carregarConteudo('caracteristica'); return false;">Característica</a></li>
                                    <li><a class="dropdown-item" id="cargo" href="#" onclick="carregarConteudo('cargo'); return false;">Cargo</a></li>
                                    <li><a class="dropdown-item" id="categoria" href="#" onclick="carregarConteudo('categoria'); return false;">Categoria</a></li>
                                    <li><a class="dropdown-item" id="clientes" href="#" onclick="carregarConteudo('clientes'); return false;">Clientes</a></li>
                                    <li><a class="dropdown-item" id="linha" href="#" onclick="carregarConteudo('linha'); return false;">Linha</a></li>
                                    <li><a class="dropdown-item" id="localestoque" href="#" onclick="carregarConteudo('localestoque'); return false;">Local Estoque</a></li>
                                    <li><a class="dropdown-item" id="marca" href="#" onclick="carregarConteudo('marca'); return false;">Marca</a></li>
                                    <li><a class="dropdown-item" id="perfil" href="#" onclick="carregarConteudo('perfil'); return false;">Perfil</a></li>
                                    <li><a class="dropdown-item" id="permissoes" href="#" onclick="carregarConteudo('permissoes'); return false;">Permissões</a></li>
                                    <li><a class="dropdown-item" id="servico" href="#" onclick="carregarConteudo('servico'); return false;">Serviços</a></li>
                                    <li><a class="dropdown-item" id="tipoperfil" href="#" onclick="carregarConteudo('tipoperfil'); return false;">Tipo Perfil</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="content" id="conteudoPrincipal">
            </div>
        </div>
    </div>
</div>
<%--<div class="pgFooter">--%>
<%--    <div class="container my-auto">--%>
<%--        <div class="text-center my-auto copyright"><span>Copyright © Trisceles Corp 2024</span></div>--%>
<%--    </div>--%>
<%--</div>--%>
</body>
</html>