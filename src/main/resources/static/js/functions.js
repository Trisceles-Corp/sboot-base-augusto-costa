function toggleFormCadastro() {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "block") {
        formClienteCadast.style.display = "none";
    } else {
        formClienteCadast.style.display = "block";
    }
}

function carregarConteudo(url) {
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

function visualizarCaracteristica(caracteristicaId, descricao, ativo) {
    document.getElementById("field_Id").value = caracteristicaId;
    document.getElementById("field_Descricao").value = descricao;
    document.getElementById("field_Active").checked = ativo === 'true';
}

function visualizarCargo(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarCategoria(categoriaId, nome, ativo) {
    document.getElementById("field_Id").value = categoriaId;
    document.getElementById("field_Name").value = nome;
    document.getElementById("field_Active").checked = ativo === 'true';
}

function visualizarDiasSemana(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarLinha(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarLocalEstoque(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarMarca(id, categoriaId, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_CategoriaId").value = categoriaId;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}


function visualizarPeriodo(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarPermissoes(id, name, description, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Description").value = description;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarServico(id, nome, tempo, valor, desconto, comissao, observacao, ativo) {
    console.log(id);
    document.getElementById("field_Id'").value = id;
    document.getElementById("field_Nome").value = nome;
    document.getElementById("field_Tempo").value = tempo;
    document.getElementById("field_Valor").value = valor;
    document.getElementById("field_Desconto").value = desconto;
    document.getElementById("field_Comissao").value = comissao;
    document.getElementById("field_Observacao").value = observacao;
    document.getElementById("field_Ativo").checked = ativo === 'true';
}

function visualizarTipo(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarPerfil(id, tipoperfilid, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_TipoPerfilId").value = tipoperfilid;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarDiasSemana(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function confirmarExclusao(event) {
    event.preventDefault();
    var confirmacao = confirm("Deseja excluir o registro?");
    if (confirmacao) {
        event.target.closest('form').submit();
    }
}

function visualizarProduto(id, codigoInterno, nome, codigoBarras, marcaId, linhaId, caracteristicaId, estoqueMinimo, custo, valorVenda, comissao, ativo) {
    console.log(id);
    document.getElementById("field_Id").value = id;
    document.getElementById("field_CodigoInterno").value = codigoInterno;
    document.getElementById("field_Name").value = nome;
    document.getElementById("field_CodigoBarras").value = codigoBarras;
    document.getElementById("field_MarcaId").value = marcaId;
    document.getElementById("field_LinhaId").value = linhaId;
    document.getElementById("field_CaracteristicaId").value = caracteristicaId;
    document.getElementById("field_EstoqueMinimo").value = estoqueMinimo;
    document.getElementById("field_Custo").value = custo;
    document.getElementById("field_valorVenda").value = valorVenda;
    document.getElementById("field_Comissao").value = comissao;
    document.getElementById("field_Active").checked = ativo === 'true';
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

function visualizarFornecedor(usuarioId, enderecoId, cargoId, perfilId, nome, sobrenome, cpfCnpj, email, profissao, ddiCelular, dddCelular, celular, ddiTelefone, dddTelefone, telefone, cep, logradouro, numero, complemento, bairro, cidade, uf, observacao) {
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
    document.getElementById("inputEmail").value = email;
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
