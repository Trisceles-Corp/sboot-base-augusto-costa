function toggleFormCadastro() {
    const formCadastro = document.getElementById("form-cadastro");
    if (formCadastro.style.display === "block") {
        formCadastro.style.display = "none";
    } else {
        formCadastro.style.display = "block";
    }
}

function toggleCloseCadastro() {
    const formCadastro = document.getElementById("form-cadastro");
    if (formCadastro.style.display === "block") {
        formCadastro.style.display = "none";
    } else {
        formCadastro.style.display = "none";
    }
    formCadastro.reset();
}

function carregarConteudo(url) {

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

function verificarNomeAntesDeSalvar() {
    const nomeInput = document.getElementById('field_Name');
    const nome = nomeInput.value.trim();

    if (nome === '') {
        alert('O campo Nome é obrigatório e não pode estar vazio.');
        return false;
    }
    return true;
}

function visualizarCaracteristica(caracteristicaId, descricao) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    document.getElementById("field_Id").value = caracteristicaId;
    document.getElementById("field_Nome").value = descricao;
}

function visualizarCargo(id, name) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Nome").value = name;
}

function visualizarSituacaoAgendamento(id, name) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Nome").value = name;
}

function visualizarCompras(contexto, id, localEstoqueId, situacaoCompraId, valorTotal, dataCriacao) {
    const formCadastro = document.getElementById("form-cadastro");
    const botaoSalvar = document.getElementById("salvar-cadastro");

    if (formCadastro.style.display === "none") {
        formCadastro.style.display = "block";
    } else {
        formCadastro.style.display = "block";
    }
    const situacaoCompraIdNumerico = Number(situacaoCompraId);
    const dataCriacaoDate = new Date(dataCriacao);
    const dataFormatada = dataCriacaoDate.toLocaleDateString('pt-BR', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
    });
    const camposHabilitados = situacaoCompraIdNumerico === 1;
    document.getElementById("field_LocalEstoqueId").disabled = !camposHabilitados;
    document.getElementById("field_SituacaoCompraId").disabled = !camposHabilitados;
    botaoSalvar.disabled = !camposHabilitados;

    document.getElementById("field_Id").value = id;
    document.getElementById("field_LocalEstoqueId").value = localEstoqueId;
    document.getElementById("field_SituacaoCompraId").value = situacaoCompraId;
    document.getElementById("field_ValorTotal").value = valorTotal;
    document.getElementById("field_DataCriacao").value = dataFormatada;

    fetch(`${contexto}/compra/produtos/${id}`)
        .then(response => response.json())
        .then(data => atualizarTabelaProdutos(data))
        .catch(error => console.error('Erro ao buscar produtos da compra:', error));
}

function visualizarSaidas(contexto, id, localEstoqueId, solicitanteId, valorTotal, dataCriacao) {
    const formCadastro = document.getElementById("form-cadastro");
    if (formCadastro.style.display === "none") {
        formCadastro.style.display = "block";
    } else {
        formCadastro.style.display = "block";
    }
    const dataCriacaoDate = new Date(dataCriacao);
    const dataFormatada = dataCriacaoDate.toLocaleDateString('pt-BR', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
    });
    document.getElementById("field_Id").value = id;
    document.getElementById("field_LocalEstoqueId").value = localEstoqueId;
    document.getElementById("field_SolicitanteId").value = solicitanteId;
    document.getElementById("field_ValorTotal").value = valorTotal;
    document.getElementById("field_DataCriacao").value = dataFormatada;

    fetch(`${contexto}/saida/produtos/${id}`)
        .then(response => response.json())
        .then(data => atualizarTabelaProdutos(data))
        .catch(error => console.error('Erro ao buscar produtos da compra:', error));
}

function atualizarTabelaProdutos(produtos) {
    const tabela = document.getElementById("tabelaDadosProdutos").getElementsByTagName('tbody')[0];
    tabela.innerHTML = ''; // Limpar tabela existente

    produtos.forEach(produto => {
        let linha = tabela.insertRow();
        let celulaAcao = linha.insertCell(0);
        celulaAcao.innerHTML = '...'; // Adicione elementos de ação conforme necessário
        linha.insertCell(1).innerText = produto.produto.descricaoProduto;
        linha.insertCell(2).innerText = produto.valorUnitario;
        linha.insertCell(3).innerText = produto.quantidade;
        linha.insertCell(4).innerText = produto.valorTotal;
    });
}

function visualizarCategoria(categoriaId, nome) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    document.getElementById("field_Id").value = categoriaId;
    document.getElementById("field_Nome").value = nome;
}

function visualizarDiasSemana(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarLinha(id, name) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Nome").value = name;
}

function visualizarLocalEstoque(id, name) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Nome").value = name;
}

function visualizarMarca(id, name, active) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Nome").value = name;
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

function visualizarServico(id, nome, tempo, valor, desconto, comissao, observacao) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Nome").value = nome;
    document.getElementById("field_Tempo").value = tempo;
    document.getElementById("field_Valor").value = valor;
    document.getElementById("field_Desconto").value = desconto;
    document.getElementById("field_Comissao").value = comissao;
    document.getElementById("field_Observacao").value = observacao;
}

function visualizarTipo(id, name, active) {
    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = name;
    document.getElementById("field_Active").checked = active === 'true';
}

function visualizarPerfil(id, tipoperfilid, name) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    document.getElementById("field_Id").value = id;
    document.getElementById("field_TipoPerfilId").value = tipoperfilid;
    document.getElementById("field_Nome").value = name;
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

function visualizarProduto(id, codigoInterno, nome, codigoBarras, marcaId, categoriaId, linhaId, caracteristicaId, estoqueMinimo, custo, valorVenda, comissao) {

    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }

    document.getElementById("field_Id").value = id;
    document.getElementById("field_CodigoInterno").value = codigoInterno;
    document.getElementById("field_Name").value = nome;
    document.getElementById("field_CodigoBarras").value = codigoBarras;
    document.getElementById("field_MarcaId").value = marcaId;
    document.getElementById("field_CategoriaId").value = categoriaId;
    document.getElementById("field_LinhaId").value = linhaId;
    document.getElementById("field_CaracteristicaId").value = caracteristicaId;
    document.getElementById("field_EstoqueMinimo").value = estoqueMinimo;
    document.getElementById("field_Custo").value = custo;
    document.getElementById("field_valorVenda").value = valorVenda;
    document.getElementById("field_Comissao").value = comissao;
}

function visualizarCliente(usuarioId, enderecoId, cargoId, perfilId, nome, sobrenome, cpfCnpj, genero, dataNascimento, email, profissao, dddCelular, celular, dddTelefone, telefone, cep, logradouro, numero, complemento, bairro, cidade, uf, observacao) {
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
    document.getElementById("inputProfissao").value = profissao;
    document.getElementById("inputDDDCel").value = dddCelular;
    document.getElementById("inputCelular").value = celular;
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

function visualizarFornecedor(usuarioId, enderecoId, cargoId, perfilId, nome, sobrenome, cpfCnpj, email, profissao, dddCelular, celular, dddTelefone, telefone, cep, logradouro, numero, complemento, bairro, cidade, uf, observacao) {
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
    document.getElementById("inputDDDCel").value = dddCelular;
    document.getElementById("inputCelular").value = celular;
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

function adicionarServico() {
    const servicoId = document.getElementById("field_ServicoId").value;
    buscarDadosServicos(servicoId);
}

function adicionarProduto() {
    const produtoId = document.getElementById("field_ProdutoId").value;
    buscarDadosProdutos(produtoId);
}

function buscarDadosServicos(servicoId) {
    $.ajax({
        url: '/agendamento/listaServicoAgendamento/' + servicoId,
        type: 'GET',
        success: function(response) {
            atualizarGridServicos(response);
        },
        error: function(error) {
            console.log("Erro ao buscar dados dos serviços: ", error);
        }
    });
}

function buscarDadosProdutos(produtoId) {
    $.ajax({
        url: '/agendamento/listaProdutoAgendamento/' + produtoId,
        type: 'GET',
        success: function(response) {
            atualizarGridProdutos(response);
        },
        error: function(error) {
            console.log("Erro ao buscar dados dos produtos: ", error);
        }
    });
}

function atualizarGridServicos(servico) {
    var tabelaServicos = document.getElementById("tabelaDadosServicos").getElementsByTagName('tbody')[0];
    var novaLinha = tabelaServicos.insertRow();
    var celulaExcluir = novaLinha.insertCell(0);
    var celulaServico = novaLinha.insertCell(1);
    var celulaValor = novaLinha.insertCell(2);
    var celulaDuracao = novaLinha.insertCell(3);

    celulaExcluir.innerHTML = '<a href="#" onclick="removerServico(this)">Remover</a>';
    celulaServico.innerHTML = servico.nome;
    celulaValor.innerHTML = servico.valor;
    celulaDuracao.innerHTML = servico.duracao;
}

function atualizarGridProdutos(produto) {
    var tabelaProduto = document.getElementById("tabelaDadosProdutos").getElementsByTagName('tbody')[0];
    var novaLinha = tabelaProduto.insertRow();
    var celulaExcluir = novaLinha.insertCell(0);
    var celulaProduto = novaLinha.insertCell(1);
    var celulaMarca = novaLinha.insertCell(2);
    var celulaLinha = novaLinha.insertCell(3);
    var celulaPreco = novaLinha.insertCell(4);
    var celulaQuantidade = novaLinha.insertCell(5);

    celulaExcluir.innerHTML = '<a href="#" onclick="removerProduto(this)">Remover</a>';
    celulaProduto.innerHTML = produto.descricaoProduto;
    celulaMarca.innerHTML = produto.marca.descricaoMarca;
    celulaLinha.innerHTML = produto.linha.descricaoLinha;
    celulaPreco.innerHTML = produto.valorVenda;
    celulaQuantidade.innerHTML = document.getElementById("field_Quantidade").outerHTML;
}

function removerServico(link) {
    var row = link.parentNode.parentNode;
    row.parentNode.removeChild(row);
}

function removerProduto(link) {
    var row = link.parentNode.parentNode;
    row.parentNode.removeChild(row);
}
