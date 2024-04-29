<%--
  Created by IntelliJ IDEA.
  User: Alexander Andrade
  Date: 02/04/2024
  Time: 14:56
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
</head>

<body>
<div>
    <div class="row" id="linha-botao-pesquisa">
        <button type="button" class="btn-cadastrar btn btn-outline-primary col-md-2 " id="btn-cadastrar" onclick="toggleFormCadastro()">Cadastrar fornecedor</button>
        <div class="input-group w-50 m-3">
            <input type="text" class="form-control" aria-label="Input text com botão dropdown">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Pesquisar fornecedor por</button>
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
    <form:form class="form-cadastro my-2" id="form-cadastro" modelAttribute="dtoUsuario" action="${pageContext.request.contextPath}/fornecedor/salvar" method="POST">
        <form:hidden path="usuarioId" id="inputUsuarioId"/>
        <form:hidden path="enderecoId" id="inputEnderecoId"/>
        <form:hidden path="cargoId" id="inputCargoId"/>
        <form:hidden path="perfilId" id="inputPerfilId"/>
        <div class="row">
            <div class="form-group col-md-2">
                <form:label path="cpfCnpj" class="form-label" for="inputCpfCnpj">CNPJ:</form:label>
                <form:input path="cpfCnpj" type="text" class="form-control" id="inputCpfCnpj" maxlength="18" required="required" />
            </div>
            <div class="form-group col-md-4">
                <form:label path="nome" class="form-label" for="inputNome">Razão Social:</form:label>
                <form:input path="nome" type="text" class="form-control" id="inputNome" maxlength="100" required="required" />
            </div>
            <div class="form-group col-md-3">
                <form:label path="sobrenome" class="form-label" for="inputSobrenome">Nome Fantasia:</form:label>
                <form:input path="sobrenome" type="text" class="form-control" id="inputSobrenome" maxlength="100" required="required"/>
            </div>
            <div class="form-group col-md-3">
                <form:label path="profissao" class="form-label" for="inputProfissao">Responsável:</form:label>
                <form:input path="profissao" type="text" class="form-control" id="inputProfissao" maxlength="100"  />
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-1">
                <form:label path="dddCelular" class="form-label" for="inputDDDCel">DDD:</form:label>
                <form:input path="dddCelular" type="number" class="form-control" id="inputDDDCel" maxlength="2" required="required" />
            </div>
            <div class="form-group col-md-2">
                <form:label path="celular" class="form-label" for="inputCelular">Celular:</form:label>
                <form:input path="celular" type="text" class="form-control" id="inputCelular" maxlength="11" required="required" />
            </div>
            <div class="form-group col-md-1">
                <form:label path="dddTelefone" class="form-label" for="inputDDDTel">DDD:</form:label>
                <form:input path="dddTelefone" type="number" class="form-control" id="inputDDDTel" maxlength="2"  />
            </div>
            <div class="form-group col-md-2">
                <form:label path="telefone" class="form-label" for="inputTelefone">Telefone:</form:label>
                <form:input path="telefone" type="text" class="form-control" id="inputTelefone" maxlength="9"  />
            </div>
            <div class="form-group col-md-3">
                <form:label path="email" class="form-label" for="inputEmail">E-mail:</form:label>
                <form:input path="email" type="email" class="form-control" id="inputEmail" maxlength="50" required="required" />
            </div>
            <div class="form-group col-md-3">
                <form:label path="observacao" class="form-label" for="inputObservacao">Site Comercial:</form:label>
                <form:input path="observacao" type="text" class="form-control" id="inputObservacao"  />
            </div>
        </div>
        <div class="row">
        </div>
        <div class="row">
            <div class="form-group col-md-2">
                <form:label path="cep" class="form-label" for="inputCEP">CEP:</form:label>
                <form:input path="cep" type="text" class="form-control" id="inputCEP" maxlength="9" required="required" />
            </div>
            <div class="form-group col-md-5">
                <form:label path="logradouro" class="form-label" for="inputlogradouro">Endereço:</form:label>
                <form:input path="logradouro" type="text" class="form-control" id="inputlogradouro" maxlength="200" required="required" />
            </div>
            <div class="form-group col-md-1">
                <form:label path="numero" class="form-label" for="inputNumero">Número:</form:label>
                <form:input path="numero" type="text" class="form-control" id="inputNumero" maxlength="10" required="required" />
            </div>
            <div class="form-group col-md-4">
                <form:label path="complemento" class="form-label" for="inputComplemento">Complemento:</form:label>
                <form:input path="complemento" type="text" class="form-control" id="inputComplemento" maxlength="100"  />
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-3">
                <form:label path="bairro" class="form-label" for="inputBairro">Bairro:</form:label>
                <form:input path="bairro" type="text" class="form-control" maxlength="100" id="inputBairro" required="required"/>
            </div>
            <div class="form-group col-md-3">
                <form:label path="cidade" class="form-label" for="inputCidade">Cidade:</form:label>
                <form:input path="cidade" type="text" class="form-control" maxlength="100" id="inputCidade" required="required"/>
            </div>
            <div class="form-group col-md-2">
                <form:label path="uf" class="form-label" for="inputEstado">Estado:</form:label>
                <form:select path="uf" id="inputEstado" class="form-control" required="required">
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
        </div>
        <div class="mt-2">
            <button type="submit" class="btn btn-primary ">Salvar</button>
            <button type="button" class="btn btn-danger  m-1" id="cancelar-cadastro" onclick="toggleCloseCadastro()">Cancelar</button>
        </div>
    </form:form>

    <!-- tabela mostrando os fornecedors cadastrados -->
    <table id="tabelaFornecedores" class="table table-bordered table-hover table-responsive my-3" >
        <thead class="table-dark">
        <tr class="gridHeader">
            <th scope="col" class="th-editar">Ações</th>
            <th scope="col">Empresa</th>
            <th scope="col">Responsável</th>
            <th scope="col">E-mail</th>
            <th scope="col">Telefone</th>
            <th scope="col">Celular</th>
            <th scope="col">Site</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dtoUsuario" items="${listaFornecedores}">
            <tr>
                <td class="cel-img-tabela-clientes">
                    <form action="${pageContext.request.contextPath}/fornecedor/delete/${dtoUsuario.usuarioId}" method="POST">
                        <img src="${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarFornecedor('${dtoUsuario.usuarioId}', '${dtoUsuario.enderecoId}', '${dtoUsuario.cargoId}', '${dtoUsuario.perfilId}', '${dtoUsuario.nome}', '${dtoUsuario.sobrenome}', '${dtoUsuario.cpfCnpj}', '${dtoUsuario.email}', '${dtoUsuario.profissao}', '${dtoUsuario.dddCelular}', '${dtoUsuario.celular}', '${dtoUsuario.dddTelefone}', '${dtoUsuario.telefone}', '${dtoUsuario.cep}', '${dtoUsuario.logradouro}', '${dtoUsuario.numero}', '${dtoUsuario.complemento}', '${dtoUsuario.bairro}', '${dtoUsuario.cidade}', '${dtoUsuario.uf}', '${dtoUsuario.observacao}'); return false;" title="Editar">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <a href="#" onclick="confirmarExclusao(event, '${pageContext.request.contextPath}/fornecedor/delete/${dtoUsuario.usuarioId}')">
                            <img src="${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Excluir">
                        </a>
                    </form>
                </td>
                <td><c:out value="${dtoUsuario.sobrenome}" /></td>
                <td><c:out value="${dtoUsuario.profissao}" /></td>
                <td><c:out value="${dtoUsuario.email}" /></td>
                <td><c:out value="${dtoUsuario.telefoneCompleto}" /></td>
                <td><c:out value="${dtoUsuario.celularCompleto}" /></td>
                <td><c:out value="${dtoUsuario.observacao}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        /* mudar cor dos icones da tabela ao passar o mouse em cima*/

        const iconeEditarfornecedor = document.querySelectorAll("img.icone-tabela-editar");
        const iconeExcluirfornecedor = document.querySelectorAll("img.icone-tabela-excluir");

        iconeEditarfornecedor.forEach((icone) => {
            icone.addEventListener("mouseover", () => {
                icone.setAttribute("src", "${pageContext.request.contextPath}/img/icones tabela clientes/escrever.png");
            });
            icone.addEventListener("mouseout", () => {
                icone.setAttribute("src", "${pageContext.request.contextPath}/img/icones tabela clientes/escrever-999.png");
            });
        });

        iconeExcluirfornecedor.forEach((icone) => {
            icone.addEventListener("mouseover", () => {
                icone.setAttribute("src", "${pageContext.request.contextPath}/img/icones tabela clientes/lixeira.png");
            });
            icone.addEventListener("mouseout", () => {
                icone.setAttribute("src", "${pageContext.request.contextPath}/img/icones tabela clientes/lixeira-999.png");
            });
        });

    </script>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVFQWjxZI3IFlSXwYEg4Gy5zIzNMAygg3IrPQe9CSbkWvFNgqP+a" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<script>

    $(document).ready(function() {
        $('#inputCEP').on('blur', aplicarBuscaCEP);

        $('#form-cadastro').submit(function(event) {
            event.preventDefault();
            var formData = $(this).serialize();

            $.ajax({
                url: '${pageContext.request.contextPath}/fornecedor/salvar',
                type: 'POST',
                data: formData,
                success: function(response) {
                    alert(response);
                },
                error: function() {
                    alert('Erro ao salvar o fornecedor.');
                }
            });
        });
    });


    aplicarMascaraCelular();
    aplicarMascaraTelefone();
    aplicarMascaraCpfCnpj();

    $('#inputCelular').on('blur', aplicarMascaraCelular);
    $('#inputTelefone').on('blur', aplicarMascaraTelefone);
    $('#inputCpfCnpj').on('blur', aplicarMascaraCpfCnpj);
    $('#inputCEP').on('blur', aplicarBuscaCEP);
    $('#inputCEP').on('keyup', aplicarMascaraCEP);

    $('#tabelaFornecedores').DataTable({
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

    function aplicarBuscaCEP() {
        var cep = $(this).val().replace(/\D/g, '');
        console.log('Evento blur acionado para inputCEP');
        console.log(cep);
        if (cep !== "") {
            var validacep = /^[0-9]{8}$/;
            if (validacep.test(cep)) {
                $.getJSON("https://viacep.com.br/ws/" + cep + "/json/", function (dados) {
                    if (!("erro" in dados)) {
                        $("#inputlogradouro").val(dados.logradouro);
                        $("#inputBairro").val(dados.bairro);
                        $("#inputCidade").val(dados.localidade);
                        $("#inputEstado").val(dados.UF);
                    } else {
                        alert("CEP não encontrado.");
                    }
                }).fail(function () {
                    alert("Erro ao buscar o CEP. Verifique sua conexão ou tente novamente mais tarde.");
                });
            } else {
                alert("Formato de CEP inválido.");
            }
        }
    }

</script>

<script>

</script>

</body>
</html>