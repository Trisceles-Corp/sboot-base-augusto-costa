<%@ page contentType="text/html;charset=UTF-8" %>
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
        var contextPath = "${pageContext.request.contextPath}";

        function toggleFormCadastro() {
            const formClienteCadast = document.getElementById("form-cadastro");
            if (formClienteCadast.style.display === "block") {
                formClienteCadast.style.display = "none";
            } else {
                formClienteCadast.style.display = "block";
            }
        }

        function visualizarCliente(usuarioId, enderecoId, cargoId, perfilId, nome, sobrenome, cpfCnpj, genero, dataNascimento, email, senha, profissao, ddiCelular, dddCelular, celular, ddiTelefone, dddTelefone, telefone, cep, logradouro, numero, complemento, bairro, cidade, uf, observacao) {
            const formClienteCadast = document.getElementById("form-cadastro");
            if (formClienteCadast.style.display === "none") {
                formClienteCadast.style.display = "block";
            } else {
                formClienteCadast.style.display = "block";
            }

            document.getElementById("inputUsuarioId").value = usuarioId;
            document.getElementById("inputEnderecoId").value = enderecoId;
            document.getElementById("inputCargoId").value = cargoId;
            document.getElementById("inputPerfilId").value = perfilId;
            document.getElementById("inputNome").value = nome;
            document.getElementById("inputSobrenome").value = sobrenome;
            document.getElementById("inputCpfCnpj").value = cpfCnpj;
            document.getElementById("inputGenero").value = genero;
            document.getElementById("inputNascimento").value = dataNascimento;
            document.getElementById("inputEmail").value = email;
            document.getElementById("inputSenha").value = senha;
            document.getElementById("inputConfirmacaoSenha").value = senha;
            document.getElementById("inputProfissao").value = profissao;
            document.getElementById("inputDDICel").value = ddiCelular;
            document.getElementById("inputDDDCel").value = dddCelular;
            document.getElementById("inputCelular").value = celular;
            document.getElementById("inputDDITel").value = ddiTelefone;
            document.getElementById("inputDDDTel").value = dddTelefone;
            document.getElementById("inputTelefone").value = telefone;
            document.getElementById("inputCEP").value = cep;
            document.getElementById("inputlogradouro").value = logradouro;
            document.getElementById("inputNumero").value = numero;
            document.getElementById("inputComplemento").value = complemento;
            document.getElementById("inputBairro").value = bairro;
            document.getElementById("inputCidade").value = cidade;
            document.getElementById("inputEstado").value = uf;
            document.getElementById("inputObservacao").value = observacao;
        }

        function carregarConteudo(nomePagina) {
            var url = contextPath + "/" + nomePagina;
            console.log(url);

            fetch(url)
                .then(response => response.text())
                .then(data => {
                    document.getElementById("mainContent").innerHTML = data;
                })
                .catch(error => {
                    console.error("Erro ao carregar a página:", error);
                });
        }

        function refreshPage() {
            // Recarrega a página atual
            location.reload();
        }
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
            <div class="contain-option w-100" id="clientes">
                <div class="my-2">
                    <img src="${pageContext.request.contextPath}/img/icon clientes/cliente-F0DD6C.png" id="cliente-img">
                    <button onclick="refreshPage()" >Clientes</button>
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
            <div class="contain-option w-100" id="estoque">
                <div class="my-2">
                    <img src="${pageContext.request.contextPath}/img/icon estoque/estoque-999.png" id="estoque-img">
                    <span class="mx-2">Estoque</span>
                </div>
                <div class="contain-sub-option">
                    <div class="sub-options px-5 py-1">
                        <button class="sub-option-item" onclick="carregarConteudo('produto')" >Produtos</button><br>
                    </div>
                    <div class="sub-options px-5 py-1">
                        <span class="sub-option-item" onclick="carregarConteudo('produto')">Fornecedores</span><br>
                    </div>
                    <div class="sub-options px-5 py-1">
                        <span class="sub-option-item" onclick="carregarConteudo('produto')">Pedidos de compra</span><br>
                    </div>
                    <div class="sub-options px-5 py-1">
                        <span class="sub-option-item" onclick="carregarConteudo('produto')">Inventário</span><br>
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
    <div class="content-main p-5" id="mainContent">
        <div class="row" id="linha-botao-pesquisa">
            <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2 " id="btn-cadastrar" onclick="toggleFormCadastro()">Cadastrar cliente</button>
            <div class="input-group w-50 m-3">
                <input type="text" class="form-control" aria-label="Input text com botão dropdown">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Pesquisar cliente por</button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">CPF / CNPJ</a>
                        <a class="dropdown-item" href="#">E-mail</a>
                        <a class="dropdown-item" href="#">Nome</a>
                        <a class="dropdown-item" href="#">Sobrenome</a>
                        <a class="dropdown-item" href="#">Telefone</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- formulário de cadastro -->
        <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="dtoUsuario" action="${pageContext.request.contextPath}/cliente/salvar" method="POST">
            <form:hidden path="usuarioId" id="inputUsuarioId"/>
            <form:hidden path="enderecoId" id="inputEnderecoId"/>
            <form:hidden path="cargoId" id="inputCargoId"/>
            <form:hidden path="perfilId" id="inputPerfilId"/>
            <div class="row">
                <div class="form-group col-md-4">
                    <form:label path="nome" for="inputNome">Nome</form:label>
                    <form:input path="nome" type="text" class="form-control" id="inputNome" maxlength="100" />
                </div>
                <div class="form-group col-md-3">
                    <form:label path="sobrenome" for="inputSobrenome">Sobrenome</form:label>
                    <form:input path="sobrenome" type="text" class="form-control" id="inputSobrenome" maxlength="200" />
                </div>
                <div class="form-group col-md-2">
                    <form:label path="cpfCnpj" for="inputCpfCnpj">CPF / CNPJ</form:label>
                    <form:input path="cpfCnpj" type="text" class="form-control" id="inputCpfCnpj" maxlength="18"  />
                </div>
                <div class="form-group col-md-1">
                    <form:label path="genero" for="inputGenero">Gênero</form:label>
                    <form:select path="genero" id="inputGenero" class="form-control">
                        <option selected>Selecione</option>
                        <option value="M" label="Masculino"></option>
                        <option value="F" label="Feminino"></option>
                        <option value="O" label="Outros"></option>
                    </form:select>
                </div>
                <div class="form-group col-md-2">
                    <form:label path="dataNascimento" for="inputNascimento">Nascimento</form:label>
                    <form:input  path="dataNascimento" type="date" class="form-control" id="inputNascimento" placeholder="mm-dd-yyyy" />
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-3">
                    <form:label path="email" for="inputEmail">E-mail</form:label>
                    <form:input path="email" type="email" class="form-control" id="inputEmail" maxlength="50"  />
                </div>
                <div class="form-group col-md-3">
                    <form:label path="senha" for="inputSenha">Senha</form:label>
                    <form:input path="senha" type="password" class="form-control" id="inputSenha"  />
                </div>
                <div class="form-group col-md-3">
                    <label for="inputConfirmacaoSenha">Confirme a Senha</label>
                    <input type="password" class="form-control" id="inputConfirmacaoSenha" >
                </div>
                <div class="form-group col-md-3">
                    <form:label path="profissao" for="inputProfissao">Profissão</form:label>
                    <form:input path="profissao" type="text" class="form-control" id="inputProfissao" maxlength="100"  />
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-1">
                    <form:label path="ddiCelular" for="inputDDICel">DDI Celular</form:label>
                    <form:input path="ddiCelular" type="number" class="form-control" id="inputDDICel" maxlength="2"  />
                </div>
                <div class="form-group col-md-2">
                    <form:label path="dddCelular" for="inputDDDCel">DDD Celular</form:label>
                    <form:input path="dddCelular" type="number" class="form-control" id="inputDDDCel" maxlength="2"  />
                </div>
                <div class="form-group col-md-3">
                    <form:label path="celular" for="inputCelular">Celular</form:label>
                    <form:input path="celular" type="text" class="form-control" id="inputCelular" maxlength="11"  />
                </div>
                <div class="form-group col-md-1">
                    <form:label path="ddiTelefone" for="inputDDITel">DDI Telefone</form:label>
                    <form:input path="ddiTelefone" type="number" class="form-control" id="inputDDITel" maxlength="2" autocomplete="true"  />
                </div>
                <div class="form-group col-md-2">
                    <form:label path="dddTelefone" for="inputDDDTel">DDD Telefone</form:label>
                    <form:input path="dddTelefone" type="number" class="form-control" id="inputDDDTel" maxlength="2"  />
                </div>
                <div class="form-group col-md-3">
                    <form:label path="telefone" for="inputTelefone">Telefone</form:label>
                    <form:input path="telefone" type="text" class="form-control" id="inputTelefone" maxlength="9"  />
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-1">
                    <form:label path="cep" for="inputCEP">CEP</form:label>
                    <form:input path="cep" type="text" class="form-control" id="inputCEP" maxlength="9"  />
                </div>
                <div class="form-group col-md-5">
                    <form:label path="logradouro" for="inputlogradouro">Endereço</form:label>
                    <form:input path="logradouro" type="text" class="form-control" id="inputlogradouro" maxlength="200"  />
                </div>
                <div class="form-group col-md-1">
                    <form:label path="numero" for="inputNumero">Número</form:label>
                    <form:input path="numero" type="text" class="form-control" id="inputNumero" maxlength="10"  />
                </div>
                <div class="form-group col-md-5">
                    <form:label path="complemento" for="inputComplemento">Complemento</form:label>
                    <form:input path="complemento" type="text" class="form-control" id="inputComplemento" maxlength="100"  />
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-3">
                    <form:label path="bairro" for="inputBairro">Bairro</form:label>
                    <form:input path="bairro" type="text" class="form-control" maxlength="100" id="inputBairro" />
                </div>
                <div class="form-group col-md-3">
                    <form:label path="cidade" for="inputCidade">Cidade</form:label>
                    <form:input path="cidade" type="text" class="form-control" maxlength="100" id="inputCidade" />
                </div>
                <div class="form-group col-md-2">
                    <form:label path="uf" for="inputEstado">Estado</form:label>
                    <form:select path="uf" id="inputEstado" class="form-control">
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
                    </form:select>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="observacao" for="inputObservacao">Observação</form:label>
                    <form:textarea path="observacao" type="text" class="form-control" id="inputObservacao"  />
                </div>
            </div>
            <div class="mt-2">
                <button type="submit" class="btn btn-primary ">Salvar</button>
                <button type="button" class="btn btn-danger  m-1" id="cancelar-cadastro">Cancelar</button>
            </div>
        </form:form>
        <script>
            /* abrir e fechar formulario */
            const formClienteCadast = document.getElementById("form-cadastro");
            const btnCancelarCadast = document.getElementById("cancelar-cadastro")

            btnCancelarCadast.addEventListener("click", () => {
                formClienteCadast.style.display = "none";
            });
        </script>

        <!-- tabela mostrando os clientes cadastrados -->
        <table id="tabelaClientes" class="table table-bordered table-hover table-responsive my-3" >
            <thead class="table-dark">
            <tr class="gridHeader">
                <th scope="col" class="th-editar">Ações</th>
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">Celular</th>
                <th scope="col">E-mail</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="dtoUsuario" items="${listaClientes}">
                    <tr>
                        <td class="cel-img-tabela-clientes">
                            <form action="${pageContext.request.contextPath}/cliente/delete/${dtoUsuario.usuarioId}" method="POST">
                                <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarCliente('${dtoUsuario.usuarioId}', '${dtoUsuario.enderecoId}', '${dtoUsuario.cargoId}', '${dtoUsuario.perfilId}', '${dtoUsuario.nome}', '${dtoUsuario.sobrenome}', '${dtoUsuario.cpfCnpj}', '${dtoUsuario.genero}', '${dtoUsuario.dataNascimento}', '${dtoUsuario.email}', '${dtoUsuario.senha}', '${dtoUsuario.profissao}', '${dtoUsuario.ddiCelular}', '${dtoUsuario.dddCelular}', '${dtoUsuario.celular}', '${dtoUsuario.ddiTelefone}', '${dtoUsuario.dddTelefone}', '${dtoUsuario.telefone}', '${dtoUsuario.cep}', '${dtoUsuario.logradouro}', '${dtoUsuario.numero}', '${dtoUsuario.complemento}', '${dtoUsuario.bairro}', '${dtoUsuario.cidade}', '${dtoUsuario.uf}', '${dtoUsuario.observacao}'); return false;" title="Editar">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/cliente/delete/${dtoUsuario.usuarioId}')">
                                    <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                                </a>
                            </form>
                        </td>
                        <th scope="row"><c:out value="${dtoUsuario.usuarioId}" /></th>
                        <td><c:out value="${dtoUsuario.nomeCompleto}" /></td>
                        <td><c:out value="${dtoUsuario.celularCompleto}" /></td>
                        <td><c:out value="${dtoUsuario.email}" /></td>
                    </tr>
                </c:forEach>
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
    <footer class="footer">
       <p> Copyright @ Trísceles Corp 2024 </p>
    </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVFQWjxZI3IFlSXwYEg4Gy5zIzNMAygg3IrPQe9CSbkWvFNgqP+a" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<script>
    $(document).ready(function(){
        aplicarMascaraCelular();
        aplicarMascaraTelefone();
        aplicarMascaraCpfCnpj();

        $('#inputCelular').on('blur', aplicarMascaraCelular);
        $('#inputTelefone').on('blur', aplicarMascaraTelefone);
        $('#inputCpfCnpj').on('blur', aplicarMascaraCpfCnpj);
        $('#inputCEP').keyup(aplicarMascaraCEP);
        $('#inputCEP').blur(aplicarMascaraCEP);

        $('#tabelaClientes').DataTable({
            "pageLength": 5,
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.11.3/i18n/pt_br.json"
            }
        });

        function aplicarMascaraCEP() {
            var cep = $(this).val().replace(/\D/g, '');
            cep = cep.replace(/^(\d{5})(\d)/, "$1-$2");
            $(this).val(cep);
        }

        function aplicarMascaraCelular() {
            $('#inputCelular').mask('00000-0000');
        }

        function aplicarMascaraTelefone() {
            $('#inputTelefone').mask('0000-0000');
        }

        function aplicarMascaraCpfCnpj() {
            var cpfCnpjOptions = {
                onKeyPress: function(cpfcnpj, e, field, options){
                    var masks = ['000.000.000-009', '00.000.000/0000-00'];
                    var mask = (cpfcnpj.length > 14) ? masks[1] : masks[0];
                    $(field).mask(mask, options);
                }
            };
            $('#inputCpfCnpj').mask('000.000.000-009', cpfCnpjOptions);
        }
    });
</script>

</body>
</html>