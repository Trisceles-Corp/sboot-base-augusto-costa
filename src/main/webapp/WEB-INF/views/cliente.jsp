<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pt-br">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Augusto Costa</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap" >
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/theme.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/functions.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVFQWjxZI3IFlSXwYEg4Gy5zIzNMAygg3IrPQe9CSbkWvFNgqP+a" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

</head>

<body>
<div class="full">
    <div class="header">
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
            <!-- classe nav-ativo para o campo da pagina atual e imagem F0DD6C (amarela) -->
            <!-- script comentado para não mudar na cor -->
            <a class="nav-option nav-ativo" id="clientes">
                <div class="contain-option w-100">
                    <div class="my-2">
                        <img src="${pageContext.request.contextPath}/img/icon clientes/cliente-F0DD6C.png" id="cliente-img">
                        <span class="mx-2">Clientes</span>
                    </div>
                    <!-- <script>
                    /* mudar cor da imagem ao passar o mouse em cima*/
                    
                    const cliente = document.getElementById("clientes");
                    const imgCliente = cliente.querySelector("img");
            
                    cliente.addEventListener("mouseover", () => {
                        imgCliente.setAttribute("src", "${pageContext.request.contextPath}/img/icon clientes/cliente-F0DD6C.png");
                    });
            
                    cliente.addEventListener("mouseout", () => {
                        imgCliente.setAttribute("src", "${pageContext.request.contextPath}/img/icon clientes/cliente-999.png");
                    });
                </script> -->
                </div>
            </a>
            <a href="estoque.html" class="nav-option" id="estoque">
                <div class="contain-option w-100">
                    <div class="my-2">
                        <img src="${pageContext.request.contextPath}/img/icon estoque/estoque-999.png" id="estoque-img">
                        <span class="mx-2">Estoque</span>
                    </div>
                    <div class="contain-sub-option">
                        <div class="sub-options px-5 py-1">
                            <span class="sub-option-item">Produtos</span><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <span class="sub-option-item">Fornecedores</span><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <span class="sub-option-item">Pedidos de compra</span><br>
                        </div>
                        <div class="sub-options px-5 py-1">
                            <span class="sub-option-item">Inventário</span><br>
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

        </div>
    </div>
    <div class="content-main p-5" id="MainContent">
        <div class="row" id="linha-botao-pesquisa">
            <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2 " id="btn-cadastrar">Cadastrar cliente</button>
            <div class="input-group w-50 m-3">
                <input type="text" class="form-control" aria-label="Input text com botão dropdown">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Pesquisar cliente por</button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">CPF</a>
                        <a class="dropdown-item" href="#">E-mail</a>
                        <a class="dropdown-item" href="#">Nome</a>
                        <a class="dropdown-item" href="#">Sobrenome</a>
                        <a class="dropdown-item" href="#">Telefone</a>
                    </div>
                </div>
            </div>
        </div>


        <!-- formulário de cadastro -->
        <form class="form-cadastro my-2" id="form-cadastro">
            <div class="row">
                <div class="form-group col-md-4">
                    <label for="inputNome">Nome</label>
                    <input type="text" class="form-control" id="inputNome" placeholder="Nome">
                </div>
                <div class="form-group col-md-3">
                    <label for="inputSobrenome">Sobrenome</label>
                    <input type="text" class="form-control" id="inputSobrenome" placeholder="Sobrenome">
                </div>
                <div class="form-group col-md-2">
                    <label for="inputCpf">CPF</label>
                    <input type="number" class="form-control" id="inputCpf" placeholder="CPF">
                </div>
                <div class="form-group col-md-1">
                    <label for="inputGenero">Gênero</label>
                    <select id="inputGenero" class="form-control">
                        <option selected>Selecione</option>
                        <option value="M" label="Masculino"></option>
                        <option value="F" label="Feminino"></option>
                        <option value="O" label="Outros"></option>
                    </select>
                </div>
                <div class="form-group col-md-2">
                    <label for="inputNascimento">Nascimento</label>
                    <input type="date" class="form-control" id="inputNascimento" placeholder="dd-mm-yyyy">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-3">
                    <label for="inputEmail">E-mail</label>
                    <input type="email" class="form-control" id="inputEmail" placeholder="E-mail">
                </div>
                <div class="form-group col-md-3">
                    <label for="inputSenha">Senha</label>
                    <input type="password" class="form-control" id="inputSenha" placeholder="Senha">
                </div>
                <div class="form-group col-md-3">
                    <label for="inputConfirmacaoSenha">Confirme a Senha</label>
                    <input type="password" class="form-control" id="inputConfirmacaoSenha" placeholder="Senha">
                </div>
                <div class="form-group col-md-3">
                    <label for="inputProfissao">Profissão</label>
                    <input type="text" class="form-control" id="inputProfissao"placeholder="Profissão">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-1">
                    <label for="inputDDICel">DDI Celular</label>
                    <input type="number" class="form-control" id="inputDDICel"placeholder="55">
                </div>
                <div class="form-group col-md-2">
                    <label for="inputDDDCel">DDD Celular</label>
                    <input type="number" class="form-control" id="inputDDDCel"placeholder="DDD">
                </div>
                <div class="form-group col-md-3">
                    <label for="inputCelular">Celular</label>
                    <input type="number" class="form-control" id="inputCelular"placeholder="Celular">
                </div>
                <div class="form-group col-md-1">
                    <label for="inputDDITel">DDI Telefone</label>
                    <input type="number" class="form-control" id="inputDDITel"placeholder="55">
                </div>
                <div class="form-group col-md-2">
                    <label for="inputDDDTel">DDD Telefone</label>
                    <input type="number" class="form-control" id="inputDDDTel"placeholder="DDD">
                </div>
                <div class="form-group col-md-3">
                    <label for="inputTelefone">Telefone</label>
                    <input type="number" class="form-control" id="inputTelefone"placeholder="Telefone">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-1">
                    <label for="inputCEP">CEP</label>
                    <input type="text" class="form-control" id="inputCEP">
                </div>
                <div class="form-group col-md-5">
                    <label for="inputEndereço">Endereço</label>
                    <input type="text" class="form-control" id="inputEndereço" placeholder="Endereço">
                </div>
                <div class="form-group col-md-1">
                    <label for="inputNumero">Número</label>
                    <input type="text" class="form-control" id="inputNumero" placeholder="Número">
                </div>
                <div class="form-group col-md-5">
                    <label for="inputComplemento">Complemento</label>
                    <input type="text" class="form-control" id="inputComplemento" placeholder="Complemento">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-3">
                    <label for="inputBairro">Bairro</label>
                    <input type="text" class="form-control" id="inputBairro">
                </div>
                <div class="form-group col-md-3">
                    <label for="inputCidade">Cidade</label>
                    <input type="text" class="form-control" id="inputCidade">
                </div>
                <div class="form-group col-md-2">
                    <label for="inputEstado">Estado</label>
                    <select id="inputEstado" class="form-control">
                        <option selected>Selecione</option>
                        <option value="AL" label="Alagoas"></option>
                        <option value="AP" label="Amapá"></option>
                        <option value="AM" label="Amazonas"></option>
                        <option value="BA" label="Bahia"></option>
                        <option value="CE" label="Ceará"></option>
                        <option value="DF" label="Distrito Federal"></option>
                        <option value="ES" label="Espírito Santo"></option>
                        <option value="GO" label="Goias"></option>
                        <option value="MA" label="Maranhão"></option>
                        <option value="MT" label="Mato Grosso"></option>
                        <option value="MS" label="Mato Grosso do Sul"></option>
                        <option value="MG" label="Minas Gerais"></option>
                        <option value="PA" label="Pará"></option>
                        <option value="PB" label="Paraíba"></option>
                        <option value="PR" label="Paraná"></option>
                        <option value="PE" label="Pernambuco"></option>
                        <option value="PI" label="Piauí"></option>
                        <option value="RJ" label="Rio de Janeiro"></option>
                        <option value="RS" label="Rio Grande do Sul"></option>
                        <option value="RO" label="Rondônia"></option>
                        <option value="RR" label="Roraima"></option>
                        <option value="SC" label="Santa Catarina"></option>
                        <option value="SP" label="São Paulo"></option>
                        <option value="SE" label="Sergipe"></option>
                        <option value="TO" label="Tocantins"></option>
                    </select>
                </div>
                <div class="form-group col-md-4">
                    <label for="inputObservacao">Observação</label>
                    <input type="text" class="form-control" id="inputObservacao"placeholder="Observação">
                </div>
            </div>
            <div class="mt-2">
                <button type="submit" class="btn btn-primary ">Salvar</button>
                <button type="button" class="btn btn-danger  m-1" id="cancelar-cadastro">Cancelar</button>
            </div>
        </form>
        <script>
            /* abrir e fechar formulario */
            const btnFormClienteCadast = document.getElementById("btn-cadastrar");
            const formClienteCadast = document.getElementById("form-cadastro");
            const btnCancelarCadast = document.getElementById("cancelar-cadastro")

            btnFormClienteCadast.addEventListener("click", () => {
                if(formClienteCadast.style.display === "block"){
                    formClienteCadast.style.display = "none";
                }else{
                    formClienteCadast.style.display = "block";
                }

            });

            btnCancelarCadast.addEventListener("click", () => {
                formClienteCadast.style.display = "none";
            });
        </script>

        <!-- tabela mostrando os clientes cadastrados -->
        <table class="table table-bordered table-hover table-responsive my-3">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">Telefone</th>
                <th scope="col">E-mail</th>
                <th scope="col" class="th-editar">Editar</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>Alex</td>
                <td>(11)98382-2323</td>
                <td>alex@gmail.com</td>
                <td class="cel-img-tabela-clientes"><img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" title="Editar"><img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir"></td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Mary</td>
                <td>(19)98122-2543</td>
                <td>mary@gmail.com</td>
                <td class="cel-img-tabela-clientes"><img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" title="Editar"><img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir"></td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td>Mônica</td>
                <td>(11)98434-2509</td>
                <td>monica@gmail.com</td>
                <td class="cel-img-tabela-clientes"><img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" title="Editar"><img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir"></td>
            </tr>
            </tbody>
        </table>
        <script>
            /* mudar cor dos icones da tabela ao passar o mouse em cima*/

            const iconeEditarCliente = document.querySelectorAll("img.icone-tabela-editar");
            const iconeExcluirCliente = document.querySelectorAll("img.icone-tabela-excluir");

            iconeEditarCliente.forEach((icone) => {
                icone.addEventListener("mouseover", () => {
                    icone.setAttribute("src", "${pageContext.request.contextPath}/img/icones tabela clientes/escrever.png");
                });
                icone.addEventListener("mouseout", () => {
                    icone.setAttribute("src", "${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png");
                });
            });

            iconeExcluirCliente.forEach((icone) => {
                icone.addEventListener("mouseover", () => {
                    icone.setAttribute("src", "${pageContext.request.contextPath}/img/icones tabela clientes/lixeira.png");
                });
                icone.addEventListener("mouseout", () => {
                    icone.setAttribute("src", "${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png");
                });
            });

        </script>
    </div>
    <footer>
        <p>Copyright @ Trísceles Corp 2024</p>
    </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVFQWjxZI3IFlSXwYEg4Gy5zIzNMAygg3IrPQe9CSbkWvFNgqP+a" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

</body>


</html>