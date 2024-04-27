package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.dto.dtoAgendamento;
import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.persistence.repository.AgendamentoRepository;
import br.com.augustocosta.acs.persistence.repository.ServicosAgendamentoRepository;
import br.com.augustocosta.acs.persistence.repository.SituacaoAgendamentoRepository;
import br.com.augustocosta.acs.persistence.repository.VendaRepository;
import br.com.augustocosta.acs.persistence.repository.VendaProdutoRepository;
import br.com.augustocosta.acs.persistence.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final ServicosAgendamentoRepository servicosAgendamentoRepository;
    private final SituacaoAgendamentoRepository situacaoRepository;
    private final VendaRepository vendaRepository;
    private final VendaProdutoRepository vendaProdutoRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository repository, ServicosAgendamentoRepository servicosAgendamentoRepository, SituacaoAgendamentoRepository situacaoRepository, VendaRepository vendaRepository, VendaProdutoRepository vendaProdutoRepository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.servicosAgendamentoRepository = servicosAgendamentoRepository;
        this.situacaoRepository = situacaoRepository;
        this.vendaRepository = vendaRepository;
        this.vendaProdutoRepository = vendaProdutoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public void create(dtoAgendamento dados) {
        tblAgendamento agendamento = createAgendamento(dados.getAgendamento());

        // Processa e salva os serviços associados ao agendamento
        dados.getServico().forEach(servicoDTO -> {
            tblServicosAgendamento servicoAgendamento = new tblServicosAgendamento();
            servicoAgendamento.setAgendamento(agendamento);
            servicoAgendamento.setServico(servicoDTO);
            servicoAgendamento.setValorUnitario(servicoDTO.getValor());
            servicoAgendamento.setQuantidade(1);
            servicoAgendamento.setAtivo(true);
            servicoAgendamento.setDataCriacao(LocalDateTime.now());
            servicoAgendamento.setDataAlteracao(LocalDateTime.now());
            servicosAgendamentoRepository.save(servicoAgendamento);
        });

        // Cria a venda automaticamente vinculada ao agendamento
        tblVenda venda = new tblVenda();
        venda.setAgendamento(agendamento);
        venda.setLocalEstoque(dados.getLocalEstoque());
        venda.setValorTotal(0.0);
        venda.setEstoque(false);
        venda.setAtivo(true);
        venda.setDataCriacao(LocalDateTime.now());
        venda.setDataAlteracao(LocalDateTime.now());
        venda = vendaRepository.save(venda);

        // Processa e salva os produtos vendidos
        final Double[] valorTotalVenda = {0.0};
        tblVenda finalVenda = venda;
        dados.getProduto().forEach(produtoDTO -> {
            tblProduto produto = produtoRepository.findById(produtoDTO.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            BigDecimal valorUnitario = BigDecimal.valueOf(produto.getValorVenda());
            BigDecimal quantidade = new BigDecimal(produtoDTO.getQuantidade());
            BigDecimal valorTotal = valorUnitario.multiply(quantidade);

            tblVendaProduto vendaProduto = new tblVendaProduto();
            vendaProduto.setVenda(finalVenda);
            vendaProduto.setProduto(produto);
            vendaProduto.setValorUnitario(produto.getValorVenda());
            vendaProduto.setQuantidade(produtoDTO.getQuantidade());
            vendaProduto.setValorTotal(valorTotal.doubleValue());
            vendaProduto.setAtivo(true);
            vendaProduto.setDataCriacao(LocalDateTime.now());
            vendaProduto.setDataAlteracao(LocalDateTime.now());
            vendaProdutoRepository.save(vendaProduto);

            valorTotalVenda[0] = valorTotalVenda[0] + vendaProduto.getValorTotal();
        });

        // Atualiza o valor total da venda
        venda.setValorTotal(valorTotalVenda[0]);
        vendaRepository.save(venda);
    }

    public tblAgendamento createAgendamento(tblAgendamento table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public List<tblAgendamento> getAll() {
        return repository.findAll();
    }

    public Optional<tblAgendamento> getById(Integer id) {
        return repository.findById(id);
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
}