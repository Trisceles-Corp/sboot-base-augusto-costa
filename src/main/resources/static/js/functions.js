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
    const situacaoCompra = document.getElementById("field_SituacaoCompraId");
    const localEstoque = document.getElementById("field_LocalEstoqueId");
    const tabela = document.getElementById("tabelaDadosProdutos");
    const inserir = document.getElementById("div-inserir");

    if (formCadastro.style.display === "none") {
        formCadastro.style.display = "block";
    } else {
        formCadastro.style.display = "block";
    }

    if(situacaoCompra !== null){
        situacaoCompra.value = 1;
    }

    if(localEstoque !== null){
        localEstoque.value = 1;
    }

    if(tabela !== null){
        const thead = tabela.getElementsByTagName('thead')[0];
        const tbody = tabela.getElementsByTagName('tbody')[0];
        // Limpar o conteúdo existente do thead e tbody
        thead.innerHTML = '';
        tbody.innerHTML = '';
    }

    if(inserir !== null){
        if(inserir.display === "none" ){
            inserir.display = "inline"
        }
        else{
            inserir.display = "inline"
        }
    }
}

function toggleFormCadastroCaixa() {
    const formCadastro = document.getElementById("form-cadastro");

    if (formCadastro.style.display === "block") {
        formCadastro.style.display = "none";
    } else {
        formCadastro.style.display = "block";
    }

    if (formCadastro) {
        formCadastro.reset();
        ajustarCamposAposReset();
    }
}

function ajustarCamposAposReset() {
    const caixaId = document.getElementById("field_Id");
    const respAbertura = document.getElementById("field_ResponsavelAbertura");
    const respFechamento = document.getElementById("field_ResponsavelFechamento");
    const valorAbertura = document.getElementById("field_ValorAbertura");
    const botaoSalvar = document.getElementById("salvar-cadastro");

    caixaId.value = null;
    respAbertura.readOnly = false;
    valorAbertura.readOnly = false;
    respFechamento.readOnly = true;
    botaoSalvar.disabled = true;
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

function toggleCloseCadastroFornecedor() {
    const formCadastro = document.getElementById("form-cadastro");
    document.getElementById("inputUsuarioId").value = null;
    document.getElementById("inputEnderecoId").value = null;
    document.getElementById("inputCargoId").value = null;
    document.getElementById("inputPerfilId").value = null;

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
            const mainContent = document.getElementById("mainContent");
            mainContent.innerHTML = data;
            AgendamentoReadOnly();
            const searchDataInicial = document.getElementById('searchDataInicial');
            const searchDataFinal = document.getElementById('searchDataFinal');
            if (searchDataInicial && searchDataFinal) {
                definirDatasIniciaisEFinais();
            }
        })
        .catch(error => console.error('Erro ao carregar a página secundária:', error));
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

function verificarValoresMovimentacaoAntesDeSalvar() {
    const justificativaInput = document.getElementById('field_observacao').value;

    if (justificativaInput === null || justificativaInput === "") {
        Swal.fire({
            icon: 'warning',
            title: 'Atenção!',
            text: 'Justificativa movimentação é obrigatória.',
            // footer: '<a href="#">Precisa de ajuda?</a>',
            confirmButtonText: 'Entendi',
            confirmButtonColor: '#3085d6',
        });
        return false;
    } else {
        coletarDadosPagamentos();
        return true;
    }
}

function verificarCamposAgendamentoAntesDeSalvar() {
    const table = document.getElementById('tabelaDadosServicos');
    const tbody = table.getElementsByTagName('tbody')[0];

    if (tbody.rows.length === 0) {
        Swal.fire({
            icon: 'warning',
            title: 'Aviso',
            text: 'Adicione o serviço para criar o agendamento, serviço obrigatório.',
        });
        return false;
    }

    coletarDadosFormulario();

    var camposObrigatorios = [
        "field_ClienteId",
        "field_ColaboradorId",
        "inputData",
        "field_HoraAgendamento",
        "field_SituacaoId"
    ];

    for (var i = 0; i < camposObrigatorios.length; i++) {
        var campo = document.getElementById(camposObrigatorios[i]);
        if (campo.value === "") {
            campo.focus();
            Swal.fire({
                icon: 'error',
                title: 'Erro',
                text: 'Por favor, preencha todos os campos obrigatórios.',
            });
            return false;
        }
    }
    return true;
}

function verificarValoresPagamentosAntesDeSalvar() {
    const valorComandaInput = document.getElementById('field_valorComanda');
    const valorTotalPgtoInput = document.getElementById('field_valorTotalPgto');
    var valorComanda = parseFloat(valorComandaInput.value);
    var valorPagamento = parseFloat(valorTotalPgtoInput.value);

    if (valorComanda !== valorPagamento) {
        Swal.fire({
            icon: 'warning',
            title: 'Atenção!',
            text: 'O valor de pagamento está divergente do valor da comanda!',
            // footer: '<a href="#">Precisa de ajuda?</a>',
            confirmButtonText: 'Entendi',
            confirmButtonColor: '#3085d6',
        });
        return false;
    } else {
        coletarDadosPagamentos();
        return true;
    }
}

function visualizarCaracteristica(caracteristicaId, descricao) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    window.scrollTo(0, 0);

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
    window.scrollTo(0, 0);

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
    window.scrollTo(0, 0);

    document.getElementById("field_Id").value = id;
    document.getElementById("field_Nome").value = name;
}

function visualizarCompras(contexto, id, localEstoqueId, situacaoCompraId, valorTotal, dataCriacao) {
    const formCadastro = document.getElementById("form-cadastro");
    const botaoSalvar = document.getElementById("salvar-cadastro");
    const botaoFinalizar = document.getElementById("finalizar-cadastro");
    const situacaoCompraIdNumerico = Number(situacaoCompraId);
    const botaoinserir = document.getElementById("buttonCompras");

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
    const camposHabilitados = situacaoCompraIdNumerico === 1;
    document.getElementById("field_LocalEstoqueId").disabled = !camposHabilitados;
    document.getElementById("field_SituacaoCompraId").disabled = !camposHabilitados;
    botaoSalvar.disabled = !camposHabilitados;
    botaoinserir.disabled = !camposHabilitados;
    botaoFinalizar.disabled = camposHabilitados;

    document.getElementById("field_Id").value = id;
    document.getElementById("field_LocalEstoqueId").value = localEstoqueId;
    document.getElementById("field_SituacaoCompraId").value = situacaoCompraId;
    document.getElementById("field_ValorTotal").value = valorTotal;
    document.getElementById("field_DataCriacao").value = dataFormatada;

    window.scrollTo(0, 0);

    fetch(`${contexto}/compra/produtos/${id}`)
        .then(response => response.json())
        .then(data => atualizarTabelaProdutosCompra(data,situacaoCompraId))
        .catch(error => console.error('Erro ao buscar produtos da compra:', error));
}

function visualizarSaidas(contexto, id, localEstoqueId, solicitanteId, valorTotal, dataCriacao) {
    const formCadastro = document.getElementById("form-cadastro");
    const botaoFinalizar = document.getElementById("finalizar-cadastro");

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

    window.scrollTo(0, 0);

    fetch(`${contexto}/saida/produtos/${id}`)
        .then(response => response.json())
        .then(data => atualizarTabelaProdutosSaida(data))
        .catch(error => console.error('Erro ao buscar produtos da compra:', error));
}

function atualizarTabelaProdutos(produtos) {
    const tabela = document.getElementById("tabelaDadosProdutos").getElementsByTagName('tbody')[0];
    tabela.innerHTML = ''; // Limpar tabela existente

    if (tabela.style.display === "none") {
        tabela.style.display = "block";
    } else {
        tabela.style.display = "block";
    }

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

function atualizarTabelaProdutosCompra(produtos, situacaoCompraId) {
    const tabela = document.getElementById("tabelaDadosProdutos");
    const thead = tabela.getElementsByTagName('thead')[0];
    const tbody = tabela.getElementsByTagName('tbody')[0];
    const situacaoCompraIdNumerico = Number(situacaoCompraId);

    // Limpar o conteúdo existente do thead e tbody
    thead.innerHTML = '';
    tbody.innerHTML = '';

    // Criar o cabeçalho da tabela
    let cabecalho = thead.insertRow();
    cabecalho.className = 'gridHeader';
    cabecalho.insertCell(0).outerHTML = '<th scope="col" class="th-editar">Ações</th>';
    cabecalho.insertCell(1).outerHTML = '<th scope="col">Id Produto</th>';
    cabecalho.insertCell(2).outerHTML = '<th scope="col">Produto</th>';
    cabecalho.insertCell(3).outerHTML = '<th scope="col">Valor Unitário</th>';
    cabecalho.insertCell(4).outerHTML = '<th scope="col">Quantidade</th>';
    cabecalho.insertCell(5).outerHTML = '<th scope="col">Total Produto</th>';

    // Preencher o corpo da tabela com os dados dos produtos
    produtos.forEach(produto => {
        let linha = tbody.insertRow();
        let celulaAcao = linha.insertCell(0);
        celulaAcao.className = 'cel-img-tabela-clientes';

        if(situacaoCompraIdNumerico === 1){
            celulaAcao.innerHTML = '<a href="#" onclick="removerProduto(this)"><img src="../img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Remover"></a>';
        } else{
            celulaAcao.innerHTML = '<img src="../img/icon%20estoque/estoque-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Remover">';
        }

        linha.insertCell(1).innerText = produto.produto.id;
        linha.insertCell(2).innerText = produto.produto.descricaoProduto;
        linha.insertCell(3).innerText = produto.valorUnitario.toFixed(2);
        linha.insertCell(4).innerText = produto.quantidade;
        linha.insertCell(5).innerText = produto.valorTotal.toFixed(2);
    });
}

function atualizarTabelaProdutosSaida(produtos) {
    const tabela = document.getElementById("tabelaDadosProdutos");
    const thead = tabela.getElementsByTagName('thead')[0];
    const tbody = tabela.getElementsByTagName('tbody')[0];

    // Limpar o conteúdo existente do thead e tbody
    thead.innerHTML = '';
    tbody.innerHTML = '';

    // Criar o cabeçalho da tabela
    let cabecalho = thead.insertRow();
    cabecalho.className = 'gridHeader';
    cabecalho.insertCell(0).outerHTML = '<th scope="col" class="th-editar">Ações</th>';
    cabecalho.insertCell(1).outerHTML = '<th scope="col">Id Produto</th>';
    cabecalho.insertCell(2).outerHTML = '<th scope="col">Produto</th>';
    cabecalho.insertCell(3).outerHTML = '<th scope="col">Valor Unitário</th>';
    cabecalho.insertCell(4).outerHTML = '<th scope="col">Quantidade</th>';
    cabecalho.insertCell(5).outerHTML = '<th scope="col">Total Produto</th>';

    // Preencher o corpo da tabela com os dados dos produtos
    produtos.forEach(produto => {
        let linha = tbody.insertRow();
        let celulaAcao = linha.insertCell(0);
        celulaAcao.className = 'cel-img-tabela-clientes';
        celulaAcao.innerHTML = '<a href="#" onclick="removerProduto(this)"><img src="../img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Remover"></a>';
        linha.insertCell(1).innerText = produto.produto.id;
        linha.insertCell(2).innerText = produto.produto.descricaoProduto;
        linha.insertCell(3).innerText = produto.valorUnitario.toFixed(2);
        linha.insertCell(4).innerText = produto.quantidade;
        linha.insertCell(5).innerText = produto.valorTotal.toFixed(2);
    });
}

function atualizarGridAgendamentos(contexto, dataAgenda) {
    const tabela = document.getElementById("tabelaGridAgendamento");
    const thead = tabela.getElementsByTagName('thead')[0];
    const tbody = tabela.getElementsByTagName('tbody')[0];

    thead.innerHTML = '';
    tbody.innerHTML = '';

    let cabecalho = thead.insertRow();
    cabecalho.className = 'gridHeader';
    cabecalho.insertCell(0).outerHTML = '<th scope="col" class="th-editar">Horário</th>';

    fetch(`${contexto}/agendamentos/${dataAgenda}`)
        .then(response => response.json())
        .then(agendamentos => {
            console.log(agendamentos); // Verificar os dados retornados

            // Agrupar agendamentos por colaborador
            const agendamentosPorColaborador = agendamentos.reduce((acc, agendamento) => {
                if (!acc[agendamento.colaborador]) {
                    acc[agendamento.colaborador] = [];
                    cabecalho.insertCell().outerHTML = `<th scope="col">${agendamento.colaborador}</th>`;
                }
                acc[agendamento.colaborador].push(agendamento);
                return acc;
            }, {});

            for (let hora = 9; hora <= 18; hora++) {
                let linhaHora = tbody.insertRow();
                linhaHora.insertCell(0).innerText = `${hora}:00`;

                Object.keys(agendamentosPorColaborador).forEach(colaborador => {
                    let celula = linhaHora.insertCell();
                    let agendamentosColaborador = agendamentosPorColaborador[colaborador];

                    let agendamentoEncontrado = agendamentosColaborador.find(agendamento =>
                        parseInt(agendamento.horarioIncial.split(':')[0]) === hora ||
                        (parseInt(agendamento.horarioIncial.split(':')[0]) < hora && parseInt(agendamento.horarioFinal.split(':')[0]) > hora)
                    );

                    if (agendamentoEncontrado) {
                        celula.innerHTML = `
                            <div class="agendamento">
                                ${agendamentoEncontrado.situacao !== 'Bloqueio' ? `
                                    <a href="#" onclick="carregarConteudo(contextPath + '/agendamento/${agendamentoEncontrado.agendamento.id}')">
                                        <span class="situacao-${agendamentoEncontrado.situacao}">
                                            ${agendamentoEncontrado.agendamento.cliente.nome}<br>
                                            ${agendamentoEncontrado.servico.nome}
                                        </span>
                                    </a>
                                ` : `
                                    <span class="situacao-${agendamentoEncontrado.situacao}">
                                        ${agendamentoEncontrado.agendamento.cliente.nome}<br>
                                        ${agendamentoEncontrado.servico.nome}
                                    </span>
                                `}
                            </div>`;
                    } else {
                        celula.innerHTML = '';
                    }
                });

                let linhaMeiaHora = tbody.insertRow();
                linhaMeiaHora.insertCell(0).innerText = `${hora}:30`;

                Object.keys(agendamentosPorColaborador).forEach(colaborador => {
                    let celula = linhaMeiaHora.insertCell();
                    let agendamentosColaborador = agendamentosPorColaborador[colaborador];

                    let agendamentoEncontrado = agendamentosColaborador.find(agendamento =>
                        (parseInt(agendamento.horarioIncial.split(':')[0]) === hora && parseInt(agendamento.horarioIncial.split(':')[1]) === 30) ||
                        (parseInt(agendamento.horarioIncial.split(':')[0]) < hora && parseInt(agendamento.horarioFinal.split(':')[0]) > hora) ||
                        (parseInt(agendamento.horarioIncial.split(':')[0]) === hora && parseInt(agendamento.horarioFinal.split(':')[1]) > 30)
                    );

                    if (agendamentoEncontrado) {
                        celula.innerHTML = `
                            <div class="agendamento">
                                ${agendamentoEncontrado.situacao !== 'Bloqueio' ? `
                                    <a href="#" onclick="carregarConteudo(contextPath + '/agendamento/${agendamentoEncontrado.agendamento.id}')">
                                        <span class="situacao-${agendamentoEncontrado.situacao}">
                                            ${agendamentoEncontrado.agendamento.cliente.nome}<br>
                                            ${agendamentoEncontrado.servico.nome}
                                        </span>
                                    </a>
                                ` : `
                                    <span class="situacao-${agendamentoEncontrado.situacao}">
                                        ${agendamentoEncontrado.agendamento.cliente.nome}<br>
                                        ${agendamentoEncontrado.servico.nome}
                                    </span>
                                `}
                            </div>`;
                    } else {
                        celula.innerHTML = '';
                    }
                });
            }
        })
        .catch(error => console.error('Erro ao buscar agendamentos:', error));
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
    document.getElementById("field_agendamentoId").value = agendamentoId;
    document.getElementById("field_ClienteId").value = clienteId;
    document.getElementById("field_ColaboradorId").value = colaboradorId;
    document.getElementById("field_dataAgendamento").value = dataAgendamento;
    document.getElementById("field_HoraAgendamento").value = horaAgendamento;
    document.getElementById("field_valorServicos").value = valorServicos;
    document.getElementById("field_valorProdutos").value = valorProdutos;
    document.getElementById("field_valorDescontos").value = valorDescontos;
    document.getElementById("field_valorComanda").value = valorComanda;
    document.getElementById("field_situacaoId").value = situacao;

    window.scrollTo(0, 0);

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

function pesquisarComissoes(contexto, colaboradorId, firstDay, lastDay){
    const colaborador = document.getElementById("field_ColaboradorId").value;
    const dataInicial = document.getElementById("searchDataInicial").value;
    const dataFinal = document.getElementById("searchDataFinal").value;

    const colaboradorLabel = document.getElementById("inputColaborador");
    const dataIniciailLabel = document.getElementById("inputDataInicial");
    const dataFinalLabel = document.getElementById("inputDataFinal");

    colaboradorLabel.value = colaborador;
    dataIniciailLabel.value = dataInicial;
    dataFinalLabel.value = dataFinal;

    fetch(`${contexto}/comissoes/listarComissoes/${colaboradorId}/${firstDay}/${lastDay}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha na resposta do servidor');
            }
            return response.json();
        })
        .then(data => atualizarTabelaComissoes(data))
        .catch(error => console.error('Erro ao buscar comissões do colaborador', error));
}

function atualizarTabelaComissoes(comissoes) {
    const totalServicos = document.getElementById("inputTotalServicos");
    const totalDescontos = document.getElementById("inputTotalDescontos");
    const totalComissoes = document.getElementById("inputTotalComissoes");

    const tabelaElement = document.getElementById("tabelaComissoes");
    const tbody = tabelaElement.getElementsByTagName('tbody')[0];

    var somaServicos = 0.0;
    var somaDescontos = 0.0;
    var somaComissoes = 0.0;

    if(comissoes !== null && comissoes.length > 0){
        tbody.innerHTML = '';

        comissoes.forEach(comissao => {
            let linha = tbody.insertRow();
            let celulaAcao = linha.insertCell(0);
            celulaAcao.innerHTML = '<img src="../img/icon%20estoque/estoque-999.png" class="icones-tabela icone-tabela-editar mx-2" title="Item">';
            linha.insertCell(1).innerText = comissao.agendamentoId;
            linha.insertCell(2).innerText = comissao.dataAgendamento;
            linha.insertCell(3).innerText = comissao.horaAgendamento;
            linha.insertCell(4).innerText = comissao.nomeCliente;
            linha.insertCell(5).innerText = comissao.valorServicos.toFixed(2);
            linha.insertCell(6).innerText = comissao.valorDescontos.toFixed(2);
            linha.insertCell(7).innerText = comissao.valorComissao.toFixed(2);

            somaServicos += comissao.valorServicos;
            somaDescontos += comissao.valorDescontos;
            somaComissoes += comissao.valorComissao;
        });

        totalServicos.value = somaServicos.toFixed(2);
        totalDescontos.value = somaDescontos.toFixed(2);
        totalComissoes.value = somaComissoes.toFixed(2);
        tabelaElement.style.display = 'block';
    } else {
        tabelaElement.style.display = 'none';
    }
}

function pesquisarMovimentacoes(contexto) {
    const firstDayInput = document.getElementById('searchDataInicial');
    const lastDayInput = document.getElementById('searchDataFinal')

    let today = new Date();
    let firstDay = new Date(today.getFullYear(), today.getMonth(), 1);
    let lastDay = new Date(today.getFullYear(), today.getMonth() + 1, 0);

    if(firstDayInput !== null && lastDayInput !== null){
        firstDay = firstDayInput.value;
        lastDay = lastDayInput.value;
    }

    const url = `${contexto}/caixamovimentacao/listarMovimentacoes/${firstDay}/${lastDay}`;
    fetch(url)
        .then(response => response.json())
        .then(data => atualizarTabelaMovimentacoes(data))
        .catch(error => {
            if (error instanceof SyntaxError) {
                console.error('Erro ao fazer parse do JSON:', error);
            } else {
                console.error('Erro ao buscar movimentações financeiras:', error);
            }
        });
}

function atualizarTabelaMovimentacoes(movimentacoes) {
    const totalEntradasLabel = document.getElementById("resultTotalEntradas");
    const totalSaidasLabel = document.getElementById("resultTotalSaidas");
    const tabelaElement = document.getElementById("tabelaMovimentacao");
    const tbody = tabelaElement.getElementsByTagName('tbody')[0];

    var somaEntradas = 0.0;
    var somaSaidas = 0.0;

    if(movimentacoes !== null && movimentacoes.length > 0){
        tbody.innerHTML = '';

        movimentacoes.forEach(movimento => {
            let linha = tbody.insertRow();
            let celulaAcao = linha.insertCell(0);
            celulaAcao.innerHTML = `<img src="../img/icones tabela clientes/escrever-999.png" class="icones-tabela icone-tabela-editar mx-2" onclick="visualizarCaixaMovimentacao('${movimento.id}', '${movimento.caixa.id}', '${movimento.tipoMovimentacao.id}', '${movimento.formaPagamento.id}', '${movimento.criadoPor}', '${movimento.valorMovimentacao}', '${movimento.observacao}'); return false;" title="Editar">`;
            linha.insertCell(1).innerText = movimento.dataCriacao;
            linha.insertCell(2).innerText = movimento.caixa.nome;
            linha.insertCell(3).innerText = movimento.tipoMovimentacao.descricaoMovimentacao;
            linha.insertCell(4).innerText = movimento.formaPagamento.nome;
            linha.insertCell(5).innerText = movimento.valorMovimentacao.toFixed(2);
            linha.insertCell(6).innerText = movimento.observacao;

            if(movimento.tipoMovimentacao.id === 4){
                somaEntradas += movimento.valorMovimentacao;
            } else{
                somaSaidas += movimento.valorMovimentacao;
            }
        });
        totalEntradasLabel.value = somaEntradas.toFixed(2);
        totalSaidasLabel.value = somaSaidas.toFixed(2);
        tabelaElement.style.display = 'block';
    } else {
        tabelaElement.style.display = 'none';
    }
}

function visualizarCategoria(categoriaId, nome) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    window.scrollTo(0, 0);

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
    window.scrollTo(0, 0);

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
    window.scrollTo(0, 0);

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
    window.scrollTo(0, 0);

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
    window.scrollTo(0, 0);

    document.getElementById("field_Id").value = id;
    document.getElementById("field_Nome").value = nome;
    document.getElementById("field_Tempo").value = tempo;
    document.getElementById("field_Valor").value = valor;
    document.getElementById("field_Desconto").value = desconto;
    document.getElementById("field_Comissao").value = comissao;
    document.getElementById("field_Observacao").value = observacao;
}

function visualizarBloqueio(id, colaboradorId, dataBloqueio, diaSemanaId, periodoId, horaInicial, horaFinal, motivo) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    window.scrollTo(0, 0);

    const formattedDate = formatDateForInput(dataBloqueio);

    console.log(formattedDate);

    document.getElementById("field_Id").value = id;
    document.getElementById("field_ColaboradorId").value = colaboradorId;
    document.getElementById("field_DataBloqueio").value = formattedDate;
    document.getElementById("field_DiasSemanaId").value = diaSemanaId;
    document.getElementById("field_PeriodoId").value = periodoId;
    document.getElementById("field_HoraInicial").value = horaInicial;
    document.getElementById("field_HoraFinal").value = horaFinal;
    document.getElementById("field_MotivoBloqueio").value = motivo;
}

function formatDateForInput(dateString) {
    const date = new Date(dateString);
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const year = date.getFullYear();
    return `${year}-${month}-${day}`;
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
    window.scrollTo(0, 0);

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

    window.scrollTo(0, 0);

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

function visualizarCaixaMovimentacao(id, caixaId, tipoMovimentacaoId, formaPagamentoId, colaboradorId, valor, justificativa) {

    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }

    window.scrollTo(0, 0);

    document.getElementById("field_Id").value = id;
    document.getElementById("field_CaixaId").value = caixaId;
    document.getElementById("field_TipoMovimentacaoId").value = tipoMovimentacaoId;
    document.getElementById("field_formaPagamentoId").value = formaPagamentoId;
    document.getElementById("field_ColaboradorId").value = colaboradorId;
    document.getElementById("field_valorMovimentacao").value = valor;
    document.getElementById("field_observacao").value = justificativa;
}

function visualizarCaixa(id, nome, responsavelAberturaId, responsavelAberturaEmail, dataAbertura, horaAbertura, valorAbertura, valorProvisorio) {
    const formClienteCadast = document.getElementById("form-cadastro");
    const respFechamentoElement = document.getElementById("field_ResponsavelFechamento");
    const respAberturaElement = document.getElementById("field_ResponsavelAbertura");
    const valorAberturaElement = document.getElementById("field_ValorAbertura");

    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }
    respFechamentoElement.readOnly=false;
    respAberturaElement.readOnly = true;
    valorAberturaElement.readOnly = true;
    window.scrollTo(0, 0);

    document.getElementById("field_Id").value = id;
    document.getElementById("field_Name").value = nome;
    document.getElementById("field_DataAbertura").value = dataAbertura;
    document.getElementById("field_HoraAbertura").value = horaAbertura;
    document.getElementById("field_ResponsavelAbertura").value = responsavelAberturaId;
    document.getElementById("field_Email").value = responsavelAberturaEmail;
    document.getElementById("field_ValorAbertura").value = valorAbertura;
    document.getElementById("field_ValorProvisorio").value = valorProvisorio;
}

function visualizarCliente(usuarioId, enderecoId, cargoId, perfilId, nome, sobrenome, cpfCnpj, genero, dataNascimento, email, profissao, dddCelular, celular, dddTelefone, telefone, cep, logradouro, numero, complemento, bairro, cidade, uf, observacao) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }

    window.scrollTo(0, 0);

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

function visualizarUsuario(usuarioId, enderecoId, cargoId, perfilId, nome, sobrenome, cpfCnpj, genero, dataNascimento, email, profissao, dddCelular, celular, dddTelefone, telefone, cep, logradouro, numero, complemento, bairro, cidade, uf, observacao) {
    const formClienteCadast = document.getElementById("form-cadastro");
    if (formClienteCadast.style.display === "none") {
        formClienteCadast.style.display = "block";
    } else {
        formClienteCadast.style.display = "block";
    }

    window.scrollTo(0, 0);

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

    window.scrollTo(0, 0);

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
    const btnSalvar = document.getElementById("buttonSalvar").value;
    const servicoId = document.getElementById("field_ServicoId").value;
    const table = document.getElementById("tabelaDadosServicos");
    const tbody = table.getElementsByTagName('tbody')[0];

    table.style.display = "block";

    if (tbody.rows.length === 0) {
        btnSalvar.disabled = true;
    } else {
        btnSalvar.disabled = false;
    }

    buscarDadosServicos(servicoId);
}

function horariosDisponiveis(contexto) {
    const inputDataAgenda = document.getElementById("inputData");
    const inputColaborador = document.getElementById("field_ColaboradorId").value;
    const inputHorario = document.getElementById("field_HoraAgendamento");

    let dataAgenda = inputDataAgenda.value;
    if (dataAgenda == null || dataAgenda === "") {
        dataAgenda = new Date().toISOString().split('T')[0];
    } else {
        dataAgenda = new Date(dataAgenda).toISOString().split('T')[0];
    }

    if (dataAgenda && inputColaborador) {
        fetch(`${contexto}/agendamento/horarios/${dataAgenda}/${inputColaborador}`)
            .then(response => response.json())
            .then(data => {
                while (inputHorario.options.length > 1) {
                    inputHorario.remove(1);
                }

                data.forEach(horario => {
                    const option = document.createElement("option");
                    // Formata o horário para remover os segundos
                    const horarioFormatado = horario.horario.substring(0, 5);
                    option.value = horarioFormatado;
                    option.text = horarioFormatado;
                    inputHorario.add(option);
                });
            })
            .catch(error => console.error('Erro ao buscar horários:', error));
    }
}

function adicionarCompraProduto() {
    const produtoId = document.getElementById("field_ProdutoId").value;
    const table = document.getElementById("tabelaDadosProdutos");

    if (table.style.display === "none") {
        table.style.display = "block";
    } else {
        table.style.display = "block";
    }

    buscarDadosCompraProdutos(produtoId);
}

function adicionarSaidaProduto() {
    const produtoId = document.getElementById("field_ProdutoId").value;
    const table = document.getElementById("tabelaDadosProdutos");

    if (table.style.display === "none") {
        table.style.display = "block";
    } else {
        table.style.display = "block";
    }

    buscarDadosSaidaProdutos(produtoId);
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
    const valorPagamento = parseFloat(document.getElementById("field_valorPagamento").value);
    const valorTotalPgtoElement = document.getElementById("field_valorTotalPgto");
    const table = document.getElementById("tabelaDadosPagamentos");

    if (!formaPagamentoId || parseInt(formaPagamentoId, 10) <= 0) {
        alert("O campo Forma é obrigatório e deve ser informado.");
        return;
    }
    if (!bandeiraId || parseInt(bandeiraId, 10) <= 0) {
        alert("O campo Informação é obrigatório e deve ser informado.");
        return;
    }
    if (isNaN(valorPagamento) || valorPagamento <= 0) {
        alert("O valor do pagamento deve ser maior que zero.");
        return;
    } else
    {
        var valorTotalPgto = parseFloat(valorTotalPgtoElement.value || 0);
        valorTotalPgto += valorPagamento;
        valorTotalPgtoElement.value = valorTotalPgto.toFixed(2); // Formata para duas casas decimais
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

function buscarDadosCompraProdutos(produtoId) {
    $.ajax({
        url: '/compra/listaCompraProdutos/' + produtoId,
        type: 'GET',
        success: function(response) {
            atualizarGridCompraProdutos(response);
        },
        error: function(error) {
            console.log("Erro ao buscar dados dos produtos: ", error);
        }
    });
}

function buscarDadosSaidaProdutos(produtoId) {
    $.ajax({
        url: '/saida/listaSaidaProdutos/' + produtoId,
        type: 'GET',
        success: function(response) {
            atualizarGridSaidaProdutos(response);
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

function atualizarGridCompraProdutos(produto) {
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
    var celulaValor = novaLinha.insertCell(3);
    var celulaQuantidade = novaLinha.insertCell(4);
    var celulaTotal = novaLinha.insertCell(5);

    celulaExcluir.innerHTML = '<a href="#" onclick="removerProduto(this)"><img src="../img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Remover"></a>';
    celulaId.innerHTML = produto.id;
    celulaProduto.innerHTML = produto.descricaoProduto;
    celulaValor.innerHTML = produto.valorVenda.toFixed(2);
    celulaQuantidade.innerHTML = quantidade;
    celulaTotal.innerHTML = (produto.valorVenda * quantidade).toFixed(2);
}

function atualizarGridSaidaProdutos(produto) {
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
    var celulaValor = novaLinha.insertCell(3);
    var celulaQuantidade = novaLinha.insertCell(4);
    var celulaTotal = novaLinha.insertCell(5);

    celulaExcluir.innerHTML = '<a href="#" onclick="removerProduto(this)"><img src="../img/icones tabela clientes/lixeira-999.png" class="icones-tabela icone-tabela-excluir mx-2" title="Remover"></a>';
    celulaId.innerHTML = produto.id;
    celulaProduto.innerHTML = produto.descricaoProduto;
    celulaValor.innerHTML = produto.valorVenda.toFixed(2);
    celulaQuantidade.innerHTML = quantidade;
    celulaTotal.innerHTML = (produto.valorVenda * quantidade).toFixed(2);
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

    var valorInput = document.getElementById("field_valorTotalPgto");

    if (valorInput.value && parseFloat(valorInput.value) > 0) {
        var valorAtual = parseFloat(valorInput.value);
        var valorCelula = parseFloat(row.cells[4].textContent || row.cells[4].innerText);
        var valorFinal = valorAtual - valorCelula;
        valorInput.value = valorFinal.toFixed(2);
    }

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

function coletarDadosFormularioCompras() {
    var produtos = [];
    document.querySelectorAll("#tabelaDadosProdutos tbody tr").forEach(function(row) {
        var produtoId = row.cells[1] ? row.cells[1].textContent : '';
        var descricaoProduto = row.cells[2] ? row.cells[2].textContent : '';
        var valorUnitario = row.cells[3] ? row.cells[3].textContent : '';
        var quantidade = row.cells[4] ? row.cells[4].textContent : '';
        var valorTotal = row.cells[5] ? row.cells[5].textContent : '';
        if (produtoId) {
            produtos.push({produtoId: produtoId, descricaoProduto: descricaoProduto, valorUnitario: valorUnitario, quantidade: quantidade, valorTotal: valorTotal});
        }
    });
    var form = document.getElementById("form-cadastro");
    produtos.forEach(function(produto, index) {
        form.appendChild(criarCampoOculto(`compraProdutos[${index}].produto.id`, produto.produtoId));
        form.appendChild(criarCampoOculto(`compraProdutos[${index}].produto.descricaoProduto`, produto.descricaoProduto));
        form.appendChild(criarCampoOculto(`compraProdutos[${index}].valorUnitario`, produto.valorUnitario));
        form.appendChild(criarCampoOculto(`compraProdutos[${index}].quantidade`, produto.quantidade));
        form.appendChild(criarCampoOculto(`compraProdutos[${index}].valorTotal`, produto.valorTotal));
    });
}

function coletarDadosFormularioSaidas() {
    var produtos = [];
    document.querySelectorAll("#tabelaDadosProdutos tbody tr").forEach(function(row) {
        var produtoId = row.cells[1] ? row.cells[1].textContent : '';
        var descricaoProduto = row.cells[2] ? row.cells[2].textContent : '';
        var valorUnitario = row.cells[3] ? row.cells[3].textContent : '';
        var quantidade = row.cells[4] ? row.cells[4].textContent : '';
        var valorTotal = row.cells[5] ? row.cells[5].textContent : '';
        if (produtoId) {
            produtos.push({produtoId: produtoId, descricaoProduto: descricaoProduto, valorUnitario: valorUnitario, quantidade: quantidade, valorTotal: valorTotal});
        }
    });
    var form = document.getElementById("form-cadastro");
    produtos.forEach(function(produto, index) {
        form.appendChild(criarCampoOculto(`saidaProdutos[${index}].produto.id`, produto.produtoId));
        form.appendChild(criarCampoOculto(`saidaProdutos[${index}].produto.descricaoProduto`, produto.descricaoProduto));
        form.appendChild(criarCampoOculto(`saidaProdutos[${index}].valorUnitario`, produto.valorUnitario));
        form.appendChild(criarCampoOculto(`saidaProdutos[${index}].quantidade`, produto.quantidade));
        form.appendChild(criarCampoOculto(`saidaProdutos[${index}].valorTotal`, produto.valorTotal));
    });
}

function coletarDadosPagamentos() {
    var pagamentos = [];
    document.querySelectorAll("#tabelaDadosPagamentos tbody tr").forEach(function(row) {
        var parcelas = row.cells[3].textContent || row.cells[3].innerText;
        var valor = row.cells[4].textContent || row.cells[4].innerText;
        var formaPagamentoId = row.cells[5].querySelector("input").value;
        var bandeiraId = row.cells[6].querySelector("input").value;
        var comandaId = row.cells[7].querySelector("input").value;
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

function ajustarCamposFormaPagamento() {
    const valorTotalPgtoElement = document.getElementById("field_valorTotalPgto");
    var formaPagamentoId = document.getElementById("field_formaPagamentoId").value;
    var bandeiraId = document.getElementById("field_bandeiraId");
    var parcelas = document.getElementById("field_parcelas");
    var valorPagamento = document.getElementById("field_valorPagamento");
    var valorComanda = document.getElementById("field_valorComanda").value;
    var valorTotalPgto = parseFloat(valorTotalPgtoElement.value || 0);

    // Resetando os campos para o estado padrão
    bandeiraId.disabled = false;
    parcelas.disabled = false;

    // Aplicando regras específicas com base na forma de pagamento selecionada
    switch(formaPagamentoId) {
        case "1": // Dinheiro
            bandeiraId.value = "3";
            bandeiraId.disabled = true;
            parcelas.value = "1";
            parcelas.disabled = true;
            valorPagamento.value = valorComanda - valorTotalPgto;
            break;
        case "2": // Débito
        case "4": // Pix
            bandeiraId.value = "4";
            bandeiraId.disabled = true;
            parcelas.value = "1";
            parcelas.disabled = true;
            valorPagamento.value = valorComanda - valorTotalPgto;
            break;
        case "3": // Crédito
            bandeiraId.disabled = false;
            Array.from(bandeiraId.options).forEach(function(option) {
                option.disabled = !["1", "2", "5"].includes(option.value);
            });
            parcelas.disabled = false;
            valorPagamento.value = valorComanda - valorTotalPgto;
            break;
        default:
            console.log("Forma de pagamento não reconhecida.");
    }
}

function ajustarCamposCaixaFechamento(){
    const valorProvisorio = document.getElementById("field_ValorProvisorio").value;
    const responsavelFechamento = document.getElementById("field_ResponsavelFechamento").value;
    const botaoSalvar = document.getElementById("salvar-cadastro");

    botaoSalvar.disabled = !(valorProvisorio > 0 && responsavelFechamento > 0);
}

function ajustarCamposCaixaAbertura(){
    const responsavelAbertura = document.getElementById("field_ResponsavelAbertura").value;
    const botaoSalvar = document.getElementById("salvar-cadastro");

    botaoSalvar.disabled = !(responsavelAbertura > 0);
}

function criarCampoOculto(nome, valor) {
    var input = document.createElement("input");
    input.type = "hidden";
    input.name = nome;
    input.value = valor;
    return input;
}

function definirDatasIniciaisEFinais() {
    const hoje = new Date();
    const primeiroDia = new Date(hoje.getFullYear(), hoje.getMonth(), 1);
    const ultimoDia = new Date(hoje.getFullYear(), hoje.getMonth() + 1, 0);

    const searchDataInicial = document.getElementById('searchDataInicial');
    const searchDataFinal = document.getElementById('searchDataFinal');

    if (searchDataInicial && searchDataFinal) {
        searchDataInicial.valueAsDate = primeiroDia;
        searchDataFinal.valueAsDate = ultimoDia;
    }
}
function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
}

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}

function finalizarCompra(contexto, compraId) {
    const url = `${contexto}/compra/finalizar/${compraId}`;
    if (confirm("Deseja finalizar a compra?")) {
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
            }
        })
            .then(response => {
                if (response.ok) {
                    alert("Compra finalizada com sucesso!");
                    window.location.href = `${contexto}/index?origem=compra`;
                } else {
                    alert("Erro ao finalizar a compra.");
                }
            })
            .catch(error => {
                console.error('Erro:', error);
                alert("Erro ao finalizar a compra.");
            });
    }
}

function finalizarSaida(contexto, saidaId) {
    const url = `${contexto}/saida/finalizar/${saidaId}`;
    if (confirm("Deseja finalizar a saída?")) {
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
            }
        })
            .then(response => {
                if (response.ok) {
                    alert("Saida finalizada com sucesso!");
                    window.location.href = `${contexto}/index?origem=saida`;
                } else {
                    alert("Erro ao finalizar a saída.");
                }
            })
            .catch(error => {
                console.error('Erro:', error);
                alert("Erro ao finalizar a saída.");
            });
    }
}

function clearGroup(checkbox) {
    let checkboxes = document.getElementsByName('theGroup');
    checkboxes.forEach(function(currentCheckbox) {
        if (currentCheckbox !== checkbox) {
            currentCheckbox.checked = false;
        }
    });
}

function verificarCPF(contexto) {
    const inputCpfCnpj = document.getElementById('inputCpfCnpj');
    let cpfCnpj = inputCpfCnpj.value.replace(/\D/g, '');
    const url = `${contexto}/cliente/verificarCpfCnpj/`;

    $.ajax({
        url: url + cpfCnpj,
        type: 'GET',
        success: function(response) {
            if (response) {
                inputCpfCnpj.value = '';
                Swal.fire({
                    icon: 'warning',
                    title: 'Aviso',
                    text: 'CPF/CNPJ já cadastrado.',
                });
            }
        },
        error: function(error) {
            console.log("Erro ao buscar dados do CPF/CNPJ: ", error);
        }
    });
}

function ajustaHorarioPeriodo(){
    const fieldPeriodoId = document.getElementById('field_PeriodoId');
    const fieldHoraInicial = document.getElementById('field_HoraInicial');
    const fieldHoraFinal = document.getElementById('field_HoraFinal');

    if(fieldPeriodoId !== null){
        const periodo = fieldPeriodoId.value;
        fieldHoraInicial.readOnly = true;
        fieldHoraFinal.readOnly = true;
        switch (periodo){
            case "2":
                fieldHoraInicial.value = "09:00";
                fieldHoraFinal.value = "12:00";
                break;
            case "3":
                fieldHoraInicial.value = "13:00";
                fieldHoraFinal.value = "18:00";
                break;
            case "4":
                fieldHoraInicial.value = "09:00";
                fieldHoraFinal.value = "18:00";
                break;
            default:
                fieldHoraInicial.value = "";
                fieldHoraFinal.value = "";
                fieldHoraInicial.readOnly = false;
                fieldHoraFinal.readOnly = false;
                break;
        }
    }
    console.log(fieldHoraInicial.value);
    console.log(fieldHoraFinal.value);
}

function ajustaDiasDaSemana() {
    const dataBloqueio = document.getElementById('field_DataBloqueio').value;
    const diasDaSemana = document.getElementById('field_DiasSemanaId');

    if (dataBloqueio) {
        const data = new Date(dataBloqueio);
        const diaSemana = data.getDay();
        let diaCorreto = 0;
        switch (diaSemana){
            case 0:
                diaCorreto = 2;
                break;
            case 1:
                diaCorreto = 3;
                break;
            case 2:
                diaCorreto = 4;
                break;
            case 3:
                diaCorreto = 5;
                break;
            case 4:
                diaCorreto = 6;
                break;
            case 5:
                diaCorreto = 7;
                break;
            case 6:
                diaCorreto = 1;
                break;
        }
        console.log(diaSemana);
        console.log(diaCorreto);
        diasDaSemana.value = diaCorreto;
    }
}

function updateClock() {
    const now = new Date();
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');
    const timeString = `${hours}:${minutes}:${seconds}`;
    document.getElementById('clock').textContent = timeString;
}

function showLoading() {
    document.getElementById('loading').style.display = 'block';
}

function hideLoading() {
    document.getElementById('loading').style.display = 'none';
}

function AgendamentoReadOnly(){
    const cliente = document.getElementById("field_ClienteId");
    const colaborador = document.getElementById("field_ColaboradorId");
    const dataAgendamento = document.getElementById("inputData");
    const horaAgendamento = document.getElementById("field_HoraAgendamento");
    const situacaoField = document.getElementById("field_SituacaoId");
    const divInsert = document.getElementById("div-Inserts");
    const buttonSalvar = document.getElementById("buttonSalvar");
    console.log("Oi !");
    if (situacaoField && situacaoField.value === "4") {
        if (cliente) {
            cliente.disabled = "true";
        }
        if (colaborador) {
            colaborador.disabled = "true";
        }
        if (dataAgendamento) {
            dataAgendamento.disabled = "true";
        }
        if (horaAgendamento) {
            horaAgendamento.disabled = "true";
        }
        if (situacaoField) {
            situacaoField.disabled = "true";
        }
        if (divInsert) {
            divInsert.style.display = "none";
        }
        if (buttonSalvar) {
            buttonSalvar.style.display = "none";
        }
    }
}