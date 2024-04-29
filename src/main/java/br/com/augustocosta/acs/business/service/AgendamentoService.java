package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.dto.dtoAgendamento;
import br.com.augustocosta.acs.integration.dto.dtoProdutoVenda;
import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.persistence.repository.*;
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
    private final ServicoRepository servicoRepository;
    private final LocalEstoqueRepository localEstoqueRepository;

    @Autowired
    public AgendamentoService(AgendamentoRepository repository, ServicosAgendamentoRepository servicosAgendamentoRepository, SituacaoAgendamentoRepository situacaoRepository, VendaRepository vendaRepository, VendaProdutoRepository vendaProdutoRepository, ProdutoRepository produtoRepository, ServicoRepository servicoRepository, LocalEstoqueRepository localEstoqueRepository) {
        this.repository = repository;
        this.servicosAgendamentoRepository = servicosAgendamentoRepository;
        this.situacaoRepository = situacaoRepository;
        this.vendaRepository = vendaRepository;
        this.vendaProdutoRepository = vendaProdutoRepository;
        this.produtoRepository = produtoRepository;
        this.servicoRepository = servicoRepository;
        this.localEstoqueRepository = localEstoqueRepository;
    }

    @Transactional
    public void create(dtoAgendamento dados) {
        tblAgendamento agendamento = createAgendamento(dados.getAgendamento());

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
            servicoAgendamento.setCriadoPor(1);
            servicoAgendamento.setAlteradoPor(1);
            servicosAgendamentoRepository.save(servicoAgendamento);
        });

        if(dados.getProduto() != null){
            // Cria a venda automaticamente vinculada ao agendamento
            tblVenda venda = new tblVenda();
            tblLocalEstoque estoque = localEstoqueRepository.getReferenceById(dados.getLocalEstoqueId());
            venda.setAgendamento(agendamento);
            venda.setLocalEstoque(estoque);
            venda.setValorTotal(0.0);
            venda.setEstoque(false);
            venda.setAtivo(true);
            venda.setDataCriacao(LocalDateTime.now());
            venda.setDataAlteracao(LocalDateTime.now());
            venda.setCriadoPor(1);
            venda.setAlteradoPor(1);
            venda = vendaRepository.save(venda);

            // Processa e salva os produtos vendidos
            final Double[] valorTotalVenda = {0.0};
            tblVenda finalVenda = venda;

            dados.getProduto().forEach(produtoDTO -> {
                tblProduto produto = produtoRepository.findById(produtoDTO.getProdutoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

                BigDecimal valorUnitario = BigDecimal.valueOf(produto.getValorVenda());
                BigDecimal quantidade = new BigDecimal(produtoDTO.getQuantidade());
                BigDecimal valorTotal = valorUnitario.multiply(quantidade);

                VendaProduto id = new VendaProduto(finalVenda.getId(), produto.getId());
                tblVendaProduto vendaProduto = new tblVendaProduto();
                vendaProduto.setId(id);
                vendaProduto.setVenda(finalVenda);
                vendaProduto.setProduto(produto);
                vendaProduto.setValorUnitario(produto.getValorVenda());
                vendaProduto.setQuantidade(produtoDTO.getQuantidade());
                vendaProduto.setValorTotal(valorTotal.doubleValue());
                vendaProduto.setAtivo(true);
                vendaProduto.setDataCriacao(LocalDateTime.now());
                vendaProduto.setDataAlteracao(LocalDateTime.now());
                vendaProduto.setCriadoPor(1);
                vendaProduto.setAlteradoPor(1);
                vendaProdutoRepository.save(vendaProduto);

                valorTotalVenda[0] = valorTotalVenda[0] + vendaProduto.getValorTotal();
            });

            // Atualiza o valor total da venda
            venda.setValorTotal(valorTotalVenda[0]);
            vendaRepository.save(venda);
        }
    }

    public tblAgendamento createAgendamento(tblAgendamento table) {
        table.setAtivo(true);
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setCriadoPor(1);
        table.setAlteradoPor(1);
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

    public List<dtoProdutoVenda> getProdutoByAgendamentoId(Integer agendamentoId)
    {
        List<dtoProdutoVenda> ret = new ArrayList<>();
        tblVenda venda = vendaRepository.findById(agendamentoId).orElse(null);
        List<tblVendaProduto> vendaProduto = vendaProdutoRepository.findByVenda(venda);
        vendaProduto.forEach(item -> {
            dtoProdutoVenda produtoVenda = new dtoProdutoVenda();
            produtoVenda.setProdutoId(item.getProduto().getId());
            produtoVenda.setNome(item.getProduto().getDescricaoProduto());
            produtoVenda.setMarca(item.getProduto().getMarca().getDescricaoMarca());
            produtoVenda.setLinha(item.getProduto().getLinha().getDescricaoLinha());
            produtoVenda.setPreco(item.getProduto().getValorVenda());
            produtoVenda.setProdutoId(item.getQuantidade());
            ret.add(produtoVenda);
        });
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
}