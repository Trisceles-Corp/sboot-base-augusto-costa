function atualizarDuracaoAposRemocao(tempoServico) {
    var duracao = document.getElementById("field_Duracao");
    var duracaoAtualMinutos = converteHoraParaMinutos(duracao.value);
    var tempoServicoMinutos = converteHoraParaMinutos(tempoServico);

    var totalMinutos = duracaoAtualMinutos - tempoServicoMinutos;

    duracao.value = converteMinutosParaHora(totalMinutos);
}

function converteHoraParaMinutos(hora) {
    if (!hora || !hora.includes(":")) {
        return 0;
    }
    var partes = hora.split(":");
    return parseInt(partes[0], 10) * 60 + parseInt(partes[1], 10);
}

function converteMinutosParaHora(minutos) {
    var horas = Math.floor(minutos / 60);
    var mins = minutos % 60;
    return String(horas).padStart(2, '0') + ":" + String(mins).padStart(2, '0');
}
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

function visualizarComanda(contexto, id, agendamentoId, clienteId, colaboradorId, dataAgendamento, horaAgendamento, valorServicos, valorProdutos, valorDescontos, valorComanda, situacao) {
    const formCadastro = document.getElementById("form-cadastro");
    const tabelaProduto = document.getElementById("tabelaDadosProdutos");

    if (formCadastro.style.display === "none") {
        formCadastro.style.display = "block";
    } else {
        formCadastro.style.display = "block";
    }

    if(valorProdutos > 0.0){
        if (tabelaProduto.style.display === "none") {
            tabelaProduto.style.display = "block";
        } else {
            tabelaProduto.style.display = "block";
        }
    } else {
        tabelaProduto.style.display = "none";
    }

    const dataAgendamentoDate = new Date(dataAgendamento);
    const dataFormatada = dataAgendamentoDate.toLocaleDateString('pt-BR', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
    });

    document.getElementById("field_Id").value = id;
    document.getElementById("field_ClienteId").value = clienteId;
    document.getElementById("field_ColaboradorId").value = colaboradorId;
    document.getElementById("field_dataAgendamento").value = dataAgendamento;
    document.getElementById("field_HoraAgendamento").value = horaAgendamento;
    document.getElementById("field_valorServicos").value = valorServicos;
    document.getElementById("field_valorProdutos").value = valorProdutos;
    document.getElementById("field_valorDescontos").value = valorDescontos;
    document.getElementById("field_valorComanda").value = valorComanda;
    document.getElementById("field_situacaoId").value = situacao;

    fetch(`${contexto}/comanda/servicos/${agendamentoId}`)
        .then(response => response.json())
        .then(data => atualizarTabelaServicosComanda(data))
        .catch(error => console.error('Erro ao buscar serviços da comanda:', error));

    fetch(`${contexto}/comanda/produtos/${agendamentoId}`)
        .then(response => response.json())
        .then(data => atualizarTabelaProdutosComanda(data))
        .catch(error => console.error('Erro ao buscar produtos da comanda:', error));
}

function atualizarTabelaServicosComanda(servicos) {
    const tabela = document.getElementById("tabelaDadosServicos").getElementsByTagName('tbody')[0];
    tabela.innerHTML = ''; // Limpar tabela existente

    servicos.forEach(servico => {
        let linha = tabela.insertRow();
        let celulaAcao = linha.insertCell(0);
        celulaAcao.innerHTML = '<img src="../img/icon%20estoque/estoque-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Remover">';
        linha.insertCell(1).innerText = servico.id;
        linha.insertCell(2).innerText = servico.nome;
        linha.insertCell(3).innerText = servico.valor;
        linha.insertCell(4).innerText = servico.tempo;
    });
}

function atualizarTabelaProdutosComanda(produtos) {
    const tabela = document.getElementById("tabelaDadosProdutos").getElementsByTagName('tbody')[0];
    tabela.innerHTML = ''; // Limpar tabela existente

    produtos.forEach(produto => {
        let linha = tabela.insertRow();
        let celulaAcao = linha.insertCell(0);
        celulaAcao.innerHTML = '<img src="../img/icon%20estoque/estoque-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Remover">';
        linha.insertCell(1).innerText = produto.produtoId;
        linha.insertCell(2).innerText = produto.nome;
        linha.insertCell(3).innerText = produto.marca;
        linha.insertCell(4).innerText = produto.linha;
        linha.insertCell(5).innerText = produto.preco;
        linha.insertCell(6).innerText = produto.quantidade;
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

function visualizarCaixa(id, nome, responsavelAberturaId, responsavelAberturaEmail, dataAbertura, valorAbertura, responsavelFechamentoId, dataFechamento, valorFechamento) {

    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }

    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = nome;
    document.getElementById("field_ResponsavelAbertura").value = responsavelAberturaId;
    document.getElementById("field_Email").value = responsavelAberturaEmail;
    document.getElementById("field_DataAbertura").value = dataAbertura;
    document.getElementById("field_ValorAbertura").value = valorAbertura;
    document.getElementById("field_ResponsavelFechamento").value = responsavelFechamentoId;
    document.getElementById("field_DataFechamento").value = dataFechamento;
    document.getElementById("field_ValorFechamento").value = valorFechamento;
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
    const table = document.getElementById("tabelaDadosServicos");
    table.style.display = "block";
    buscarDadosServicos(servicoId);
}

function adicionarProduto() {
    const produtoId = document.getElementById("field_ProdutoId").value;
    const table = document.getElementById("tabelaDadosProdutos");
    table.style.display = "block";
    buscarDadosProdutos(produtoId);
}

function adicionarPagamento() {
    const formaPagamentoId = document.getElementById("field_formaPagamentoId").value;
    const bandeiraId = document.getElementById("field_bandeiraId").value;
    const table = document.getElementById("tabelaDadosPagamentos");

    if (!formaPagamentoId || parseInt(formaPagamentoId, 10) <= 0) {
        alert("O campo Forma é obrigatório e deve ser informado.");
        return;
    }
    if (!bandeiraId || parseInt(bandeiraId, 10) <= 0) {
        alert("O campo Informação é obrigatório e deve ser informado.");
        return;
    }
    table.style.display = "block";
    buscarDadosPagamentos(formaPagamentoId, bandeiraId);
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

function buscarDadosPagamentos(formaPagamentoId, bandeiraId) {
    var requestFormaPagamento = $.ajax({
        url: '/comanda/listaFormasPagamento/' + formaPagamentoId,
        type: 'GET'
    });

    var requestBandeira = $.ajax({
        url: '/comanda/listaBandeiras/' + bandeiraId,
        type: 'GET'
    });

    $.when(requestFormaPagamento, requestBandeira).done(function(responseFormaPagamento, responseBandeira) {
        atualizarGridPagamentos(responseFormaPagamento[0], responseBandeira[0]);
    }).fail(function(error) {
        console.log("Erro ao buscar dados: ", error);
    });
}
function atualizarGridServicos(servico) {
    var duracao = document.getElementById("field_Duracao");
    var tabelaServicos = document.getElementById("tabelaDadosServicos").getElementsByTagName('tbody')[0];
    var novaLinha = tabelaServicos.insertRow();
    var celulaExcluir = novaLinha.insertCell(0);
    var celulaId = novaLinha.insertCell(1);
    var celulaServico = novaLinha.insertCell(2);
    var celulaValor = novaLinha.insertCell(3);
    var celulaDuracao = novaLinha.insertCell(4);

    celulaExcluir.innerHTML = '<a href="#" onclick="removerServico(this)"><img src="../img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Remover"></a>';
    celulaId.innerHTML = servico.id;
    celulaServico.innerHTML = servico.nome;
    celulaValor.innerHTML = servico.valor;
    celulaDuracao.innerHTML = servico.tempo;

    var duracaoAtualMinutos = converteHoraParaMinutos(duracao.value);
    var tempoServicoMinutos = converteHoraParaMinutos(servico.tempo);
    var totalMinutos = duracaoAtualMinutos + tempoServicoMinutos;

    duracao.value = converteMinutosParaHora(totalMinutos);
}

function atualizarGridProdutos(produto) {
    var quantidadeInput = document.getElementById("field_Quantidade");
    var quantidade = parseInt(quantidadeInput.value, 10); // Converte para número, base 10

    if (isNaN(quantidade) || quantidade === null || quantidade === "" || quantidade < 1) {
        quantidade = 1;
        quantidadeInput.value = 1;
    }

    var tabelaProdutos = document.getElementById("tabelaDadosProdutos").getElementsByTagName('tbody')[0];
    var novaLinha = tabelaProdutos.insertRow();
    var celulaExcluir = novaLinha.insertCell(0);
    var celulaId = novaLinha.insertCell(1);
    var celulaProduto = novaLinha.insertCell(2);
    var celulaMarca = novaLinha.insertCell(3);
    var celulaLinha = novaLinha.insertCell(4);
    var celulaPreco = novaLinha.insertCell(5);
    var celulaQuantidade = novaLinha.insertCell(6);

    celulaExcluir.innerHTML = '<a href="#" onclick="removerProduto(this)"><img src="../img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Remover"></a>';
    celulaId.innerHTML = produto.id;
    celulaProduto.innerHTML = produto.descricaoProduto;
    celulaMarca.innerHTML = produto.marca.descricaoMarca;
    celulaLinha.innerHTML = produto.linha.descricaoLinha;
    celulaPreco.innerHTML = produto.valorVenda;
    celulaQuantidade.innerHTML = quantidade;
}

function atualizarGridPagamentos(formaPagamento, bandeira) {
    var formaInput = document.getElementById("field_formaPagamentoId");
    var bandeiraInput = document.getElementById("field_bandeiraId");
    var comandaInput = document.getElementById("field_Id");
    var comandaId = parseInt(comandaInput.value, 10);

    var caixaInput = document.getElementById("field_CaixaId");
    if (!caixaInput.value || caixaInput.value === "0") {
        alert("O campo Caixa é obrigatório e deve ser informado.");
        return;
    }

    var parcelasInput = document.getElementById("field_parcelas");
    if (!parcelasInput.value || parseInt(parcelasInput.value, 10) <= 0) {
        alert("O campo Parcelas é obrigatório e deve ser maior que 0.");
        return;
    }
    var parcelas = parseInt(parcelasInput.value, 10);

    var valorInput = document.getElementById("field_valorPagamento");
    if (!valorInput.value || parseFloat(valorInput.value) <= 0) {
        alert("O campo Valor do Pagamento é obrigatório e deve ser maior que 0.");
        return;
    }
    var valor = parseFloat(valorInput.value);

    if (isNaN(parcelas) || parcelas === null || parcelas === "" || parcelas < 1) {
        parcelas = 1;
        parcelasInput.value = 1;
    }

    var tabelaPagamentos = document.getElementById("tabelaDadosPagamentos").getElementsByTagName('tbody')[0];
    var novaLinha = tabelaPagamentos.insertRow();
    var celulaExcluir = novaLinha.insertCell(0);
    var celulaFormaPagamento = novaLinha.insertCell(1);
    var celulaBandeira = novaLinha.insertCell(2);
    var celulaParcelas = novaLinha.insertCell(3);
    var celulaValor = novaLinha.insertCell(4);
    var celulaFormaPagamentoId = novaLinha.insertCell(5);
    var celulaBandeiraId = novaLinha.insertCell(6);
    var celulaComandaId = novaLinha.insertCell(7);

    celulaExcluir.innerHTML = '<a href="#" onclick="removerPagamento(this)"><img src="../img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Remover"></a>';
    celulaFormaPagamento.innerHTML = formaPagamento.nome;
    celulaBandeira.innerHTML = bandeira.nome;
    celulaParcelas.innerHTML = parcelas;
    celulaValor.innerHTML = valor.toFixed(2);

    celulaFormaPagamentoId.innerHTML = `<input type="hidden" name="formaPagamentoId" value="${formaPagamento.id}">`;
    celulaBandeiraId.innerHTML = `<input type="hidden" name="bandeiraId" value="${bandeira.id}">`;
    celulaComandaId.innerHTML = `<input type="hidden" name="comandaId" value="${comandaId}">`;

    celulaFormaPagamentoId.style.display = 'none';
    celulaBandeiraId.style.display = 'none';
    celulaComandaId.style.display = 'none';

    caixaInput.value = 0;
    formaInput.value = 0;
    bandeiraInput.value = 0;
    parcelasInput.value = 0;
    valorInput.value = 0.0;
}

function removerServico(link) {
    const table = document.getElementById("tabelaDadosServicos");
    const tbody = table.getElementsByTagName('tbody')[0];
    var row = link.parentNode.parentNode;
    var tempoServico = row.cells[3].innerText;

    row.parentNode.removeChild(row);

    if (tbody.rows.length === 0) {
        table.style.display = "none";
    } else {
        table.style.display = "block";
    }

    atualizarDuracaoAposRemocao(tempoServico);
}

function removerProduto(link) {
    const table = document.getElementById("tabelaDadosProdutos");
    const tbody = table.getElementsByTagName('tbody')[0];
    var row = link.parentNode.parentNode;

    row.parentNode.removeChild(row);

    if (tbody.rows.length === 0) {
        table.style.display = "none";
    } else {
        table.style.display = "block";
    }
}

function removerPagamento(link) {
    const table = document.getElementById("tabelaDadosPagamentos");
    const tbody = table.getElementsByTagName('tbody')[0];
    var row = link.parentNode.parentNode;

    row.parentNode.removeChild(row);

    if (tbody.rows.length === 0) {
        table.style.display = "none";
    } else {
        table.style.display = "block";
    }
}

function coletarDadosFormulario() {
    var servicos = [];
    document.querySelectorAll("#tabelaDadosServicos tbody tr").forEach(function(row) {
        var servicoId = row.cells[1] ? row.cells[1].textContent : '';
        var valor = row.cells[3] ? row.cells[3].textContent : '';
        var duracao = row.cells[4] ? row.cells[4].textContent : '';
        if (servicoId) {
            servicos.push({servicoId: servicoId, valor: valor, duracao: duracao});
        }
    });

    var produtos = [];
    document.querySelectorAll("#tabelaDadosProdutos tbody tr").forEach(function(row) {
        var produtoId = row.cells[1] ? row.cells[1].textContent : '';
        var quantidade = row.cells[6] ? row.cells[6].textContent : '';
        if (produtoId) {
            produtos.push({produtoId: produtoId, quantidade: quantidade});
        }
    });
    var form = document.getElementById("form-cadastro");
    servicos.forEach(function(servico, index) {
        form.appendChild(criarCampoOculto(`servico[${index}].id`, servico.servicoId));
        form.appendChild(criarCampoOculto(`servico[${index}].valor`, servico.valor));
        form.appendChild(criarCampoOculto(`servico[${index}].tempo`, servico.duracao));
    });
    produtos.forEach(function(produto, index) {
        form.appendChild(criarCampoOculto(`produto[${index}].produtoId`, produto.produtoId));
        form.appendChild(criarCampoOculto(`produto[${index}].quantidade`, produto.quantidade));
    });
}

function coletarDadosPagamentos() {
    var pagamentos = [];
    document.querySelectorAll("#tabelaDadosPagamentos tbody tr").forEach(function(row) {
        var parcelas = row.cells[3] ? row.cells[3].textContent : '';
        var valor = row.cells[4] ? row.cells[4].textContent : '';
        var formaPagamentoId = row.cells[5] ? row.cells[5].textContent : '';
        var bandeiraId = row.cells[6] ? row.cells[6].textContent : '';
        var comandaId = row.cells[7] ? row.cells[7].textContent : '';
        if (formaPagamentoId) {
            pagamentos.push({comandaId: comandaId, formaPagamentoId: formaPagamentoId, bandeiraId: bandeiraId, parcelas: parcelas, valor: valor});
        }
    });

    var form = document.getElementById("form-cadastro");
    pagamentos.forEach(function(pagamento, index) {
        form.appendChild(criarCampoOculto(`pagamentos[${index}].comanda.id`, pagamento.comandaId));
        form.appendChild(criarCampoOculto(`pagamentos[${index}].formaPagamento.id`, pagamento.formaPagamentoId));
        form.appendChild(criarCampoOculto(`pagamentos[${index}].bandeira.id`, pagamento.bandeiraId));
        form.appendChild(criarCampoOculto(`pagamentos[${index}].parcelas`, pagamento.parcelas));
        form.appendChild(criarCampoOculto(`pagamentos[${index}].valorPagamento`, pagamento.valor));
    });
}

function criarCampoOculto(nome, valor) {
    var input = document.createElement("input");
    input.type = "hidden";
    input.name = nome;
    input.value = valor;
    return input;
}