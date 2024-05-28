package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.dto.dtoAgendamento;
import br.com.augustocosta.acs.integration.dto.dtoGridAgendamento;
import br.com.augustocosta.acs.integration.dto.dtoProdutoVenda;
import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.integration.projections.prjGridAgendamento;
import br.com.augustocosta.acs.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final ServicosAgendamentoRepository servicosAgendamentoRepository;
    private final SituacaoAgendamentoRepository situacaoRepository;
    private final VendaRepository vendaRepository;
    private final VendaProdutoRepository vendaProdutoRepository;
    private final ProdutoRepository produtoRepository;
    private final ServicoRepository servicoRepository;
    private final LocalEstoqueRepository localEstoqueRepository;
    private final ComandaRepository comandaRepository;
    private final GridAgendamentoRepository gridAgendamentoRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository repository, ServicosAgendamentoRepository servicosAgendamentoRepository, SituacaoAgendamentoRepository situacaoRepository, VendaRepository vendaRepository, VendaProdutoRepository vendaProdutoRepository, ProdutoRepository produtoRepository, ServicoRepository servicoRepository, LocalEstoqueRepository localEstoqueRepository, ComandaRepository comandaRepository, GridAgendamentoRepository gridAgendamentoRepository) {
        this.repository = repository;
        this.servicosAgendamentoRepository = servicosAgendamentoRepository;
        this.situacaoRepository = situacaoRepository;
        this.vendaRepository = vendaRepository;
        this.vendaProdutoRepository = vendaProdutoRepository;
        this.produtoRepository = produtoRepository;
        this.servicoRepository = servicoRepository;
        this.localEstoqueRepository = localEstoqueRepository;
        this.comandaRepository = comandaRepository;
        this.gridAgendamentoRepository = gridAgendamentoRepository;
    }

    @Transactional
<<<<<<< HEAD
    public void create(dtoAgendamento dados, Integer userId) {
        tblAgendamento agendamento = createAgendamento(dados.getAgendamento(), userId);
=======
    public void create(dtoAgendamento dados) {
        tblAgendamento agendamento = createAgendamento(dados.getAgendamento());
>>>>>>> main
        final BigDecimal[] somaValores = {BigDecimal.valueOf(0.0)};
        final BigDecimal[] somaDescontos = {BigDecimal.valueOf(0.0)};
        final BigDecimal[] somaComissao = {BigDecimal.valueOf(0.0)};
        final BigDecimal[] somaProdutos = {BigDecimal.valueOf(0.0)};

        // Processa e salva os serviços associados ao agendamento
        dados.getServico().forEach(servicoDTO -> {
            tblServico servico = servicoRepository.getReferenceById(servicoDTO.getId());
            ServicosAgendamento id = new ServicosAgendamento(servico.getId(), agendamento.getId());
            tblServicosAgendamento servicoAgendamento = new tblServicosAgendamento();
            servicoAgendamento.setId(id);
            servicoAgendamento.setAgendamento(agendamento);
            servicoAgendamento.setServico(servico);
            servicoAgendamento.setValorUnitario(servicoDTO.getValor());
            servicoAgendamento.setQuantidade(1);
            servicoAgendamento.setAtivo(true);
            servicoAgendamento.setDataCriacao(LocalDateTime.now());
            servicoAgendamento.setDataAlteracao(LocalDateTime.now());
            servicoAgendamento.setCriadoPor(userId);
            servicoAgendamento.setAlteradoPor(userId);
            servicosAgendamentoRepository.save(servicoAgendamento);

            // Somatoria valores para abertura da comanda
            if(servico.getDesconto().compareTo(BigDecimal.ZERO) == 0){ servico.setDesconto(BigDecimal.valueOf(1.0)); }
            if(servico.getComissao().compareTo(BigDecimal.ZERO) == 0){ servico.setComissao(BigDecimal.valueOf(1.0)); }

            somaValores[0] = somaValores[0].add(servicoAgendamento.getValorUnitario());
            somaDescontos[0] = somaDescontos[0].add(servico.getValor().multiply(servico.getDesconto().divide(BigDecimal.valueOf(100))));
<<<<<<< HEAD
            somaComissao[0] = somaComissao[0].add(servico.getValor().subtract(servico.getValor().multiply(servico.getDesconto().divide(BigDecimal.valueOf(100)))).multiply(servico.getComissao().divide(BigDecimal.valueOf(100))));
=======
            somaComissao[0] = somaComissao[0].add(servico.getValor().multiply(servico.getComissao().divide(BigDecimal.valueOf(100))));
>>>>>>> main
        });

        if(dados.getProduto() != null){
            // Cria a venda automaticamente vinculada ao agendamento
            tblVenda venda = new tblVenda();
            tblLocalEstoque estoque = localEstoqueRepository.getReferenceById(dados.getLocalEstoqueId());
            venda.setAgendamento(agendamento);
            venda.setLocalEstoque(estoque);
            venda.setValorTotal(BigDecimal.valueOf(0.0));
            venda.setEstoque(false);
            venda.setAtivo(true);
            venda.setDataCriacao(LocalDateTime.now());
            venda.setDataAlteracao(LocalDateTime.now());
            venda.setCriadoPor(userId);
            venda.setAlteradoPor(userId);
            venda = vendaRepository.save(venda);

            // Processa e salva os produtos vendidos
            final BigDecimal[] valorTotalVenda = {BigDecimal.valueOf(0.0)};
            tblVenda finalVenda = venda;

            dados.getProduto().forEach(produtoDTO -> {
                tblProduto produto = produtoRepository.findById(produtoDTO.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

                BigDecimal valorUnitario = produto.getValorVenda();
                BigDecimal quantidade = new BigDecimal(produtoDTO.getQuantidade());
                BigDecimal valorTotal = valorUnitario.multiply(quantidade);

                VendaProduto id = new VendaProduto(finalVenda.getId(), produto.getId());
                tblVendaProduto vendaProduto = new tblVendaProduto();
                vendaProduto.setId(id);
                vendaProduto.setVenda(finalVenda);
                vendaProduto.setProduto(produto);
                vendaProduto.setValorUnitario(produto.getValorVenda());
                vendaProduto.setQuantidade(produtoDTO.getQuantidade());
                vendaProduto.setValorTotal(valorTotal);
                vendaProduto.setAtivo(true);
                vendaProduto.setDataCriacao(LocalDateTime.now());
                vendaProduto.setDataAlteracao(LocalDateTime.now());
                vendaProduto.setCriadoPor(userId);
                vendaProduto.setAlteradoPor(userId);
                vendaProdutoRepository.save(vendaProduto);

                valorTotalVenda[0] = valorTotalVenda[0].add(vendaProduto.getValorTotal());
            });

            // Atualiza o valor total da venda
            somaProdutos[0] = valorTotalVenda[0];
            venda.setValorTotal(valorTotalVenda[0]);
            vendaRepository.save(venda);
        }

        // Cria a comanda para o agendamento
        tblComanda comanda = new tblComanda();
        comanda.setAgendamento(agendamento);
        comanda.setValorServicos(somaValores[0]);
        comanda.setValorDescontos(somaDescontos[0]);
        comanda.setValorComissao(somaComissao[0]);
        comanda.setValorProdutos(somaProdutos[0]);
        comanda.setValorEncargos(BigDecimal.valueOf(0.0));
        comanda.setSituacao(true);
        comanda.setAtivo(true);
        comanda.setDataCriacao(LocalDateTime.now());
        comanda.setDataAlteracao(LocalDateTime.now());
        comanda.setCriadoPor(userId);
        comanda.setAlteradoPor(userId);
        comandaRepository.save(comanda);

        // Insere agendamento no grid
        tblGridAgendamento grid = new tblGridAgendamento();
        grid.setComanda(comanda);
        grid.setAgendameto(agendamento);
        grid.setServico(dados.getServico().getFirst());
        grid.setAtivo(true);
        grid.setDataCriacao(LocalDateTime.now());
        grid.setDataAlteracao(LocalDateTime.now());
        grid.setCriadoPor(userId);
        grid.setAlteradoPor(userId);
        gridAgendamentoRepository.save(grid);
    }

    public tblAgendamento createAgendamento(tblAgendamento table, Integer userId) {
        table.setAtivo(true);
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setCriadoPor(userId);
        table.setAlteradoPor(userId);
        return repository.save(table);
    }

    public List<tblAgendamento> getAll() {
        return repository.findAll();
    }

    public Optional<tblAgendamento> getById(Integer id) {
        return repository.findById(id);
    }

    public tblAgendamento getTableById(Integer id) {
        return repository.getReferenceById(id);
    }

    public List<tblAgendamento> getByCliente(tblUsuario cliente) {
        return repository.findByCliente(cliente);
    }

    public List<tblAgendamento> getByColaborador(tblUsuario colaborador) {
        return repository.findByColaborador(colaborador);
    }

    public List<tblAgendamento> getBySituacao(int situacaoId) {
        Optional<tblSituacaoAgendamento> situacao = situacaoRepository.findById(situacaoId);
        return repository.findBySituacao(situacao.orElse(null));
    }

    public List<tblAgendamento> getByDataAgendamento(Date dataAgendamento) {
        return repository.findByDataAgendamento(dataAgendamento);
    }

    public List<tblAgendamento> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblAgendamento> getInactives() {
        return repository.findByAtivoFalse();
    }

    public List<dtoProdutoVenda> getProdutoByAgendamentoId(Integer agendamentoId)
    {
        List<dtoProdutoVenda> ret = new ArrayList<>();
        if(agendamentoId != 0){
            tblVenda venda = vendaRepository.findByAgendamentoId(agendamentoId);
            List<tblVendaProduto> vendaProduto = vendaProdutoRepository.findByVenda(venda);
            vendaProduto.forEach(item -> {
                dtoProdutoVenda produtoVenda = new dtoProdutoVenda();
                produtoVenda.setProdutoId(item.getProduto().getId());
                produtoVenda.setNome(item.getProduto().getDescricaoProduto());
                produtoVenda.setMarca(item.getProduto().getMarca().getDescricaoMarca());
                produtoVenda.setLinha(item.getProduto().getLinha().getDescricaoLinha());
                produtoVenda.setPreco(item.getProduto().getValorVenda());
                produtoVenda.setQuantidade(item.getQuantidade());
                ret.add(produtoVenda);
            });
        }
        return ret;
    }

    public tblAgendamento update(tblAgendamento dados) {
        return repository.save(dados);
    }

    public void delete(Integer id, int alteradoPor) {
        tblAgendamento table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblAgendamento> table = repository.findById(id);
        return table.map(tblAgendamento::getAtivo).orElse(false);
    }

    public List<dtoGridAgendamento> convertProjectionToDto(List<prjGridAgendamento> projections) {
        List<dtoGridAgendamento> dtos = new ArrayList<>();
        for(prjGridAgendamento prj : projections) {
            dtoGridAgendamento dto = new dtoGridAgendamento();
            dtos.add(dto);
        }
        return dtos;
    }
<<<<<<< HEAD
=======

>>>>>>> main
}