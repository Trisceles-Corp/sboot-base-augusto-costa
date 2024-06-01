package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    private final CompraRepository repository;
    private final ProdutoRepository produtoRepository;
    private final CompraProdutoRepository compraProdutoRepository;
    private final LocalEstoqueRepository localRepository;
    private final SituacaoCompraRepository situacaoRepository;

    @Autowired
    public CompraService(CompraRepository repository, ProdutoRepository produtoRepository, CompraProdutoRepository compraProdutoRepository, LocalEstoqueRepository localRepository, SituacaoCompraRepository situacaoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
        this.compraProdutoRepository = compraProdutoRepository;
        this.localRepository = localRepository;
        this.situacaoRepository = situacaoRepository;
    }

    @Transactional
    public tblCompra create(tblCompra table, List<tblCompraProduto> tableCompras) {
        tblCompra compra = createCompra(table);
        final BigDecimal[] valorPedidos = {BigDecimal.valueOf(0)};
        tableCompras.forEach(compraProduto -> {
            tblProduto produto = produtoRepository.findById(compraProduto.getProduto().getId()).orElseThrow();
            tblCompraProduto tableProduto = createCompraProduto(compra, produto, compraProduto.getQuantidade(), compraProduto.getValorTotal());
            valorPedidos[0] = valorPedidos[0].add(tableProduto.getValorTotal());
        });
        compra.setValorTotal(valorPedidos[0]);
        return repository.save(compra);
    }

    @Transactional
    public tblCompra createCompra(tblCompra table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    @Transactional
    public tblCompraProduto createCompraProduto(tblCompra compra, tblProduto produto, Integer quantidade, BigDecimal valorTotal) {
        CompraProduto id = new CompraProduto(compra.getId(), produto.getId());
        tblCompraProduto tableProduto = new tblCompraProduto();
        tableProduto.setId(id);
        tableProduto.setValorUnitario(produto.getValorVenda());
        tableProduto.setQuantidade(quantidade);
        tableProduto.setValorTotal(valorTotal);
        tableProduto.setDataCriacao(LocalDateTime.now());
        tableProduto.setDataAlteracao(LocalDateTime.now());
        tableProduto.setCriadoPor(compra.getCriadoPor());
        tableProduto.setAlteradoPor(compra.getAlteradoPor());
        tableProduto.setAtivo(true);
        return compraProdutoRepository.save(tableProduto);
    }

    @Transactional
    public tblCompra update(tblCompra dados, List<tblCompraProduto> tableCompras) {
        tblCompra compra = repository.findById(dados.getId()).orElseThrow();
        List<tblCompraProduto> compraProdutos = compraProdutoRepository.findByCompra(compra);
        compraProdutoRepository.deleteAll(compraProdutos);
        final BigDecimal[] valorPedidos = {BigDecimal.valueOf(0)};

        for (tblCompraProduto item : tableCompras) {
            tblProduto produto = produtoRepository.findById(item.getProduto().getId()).orElseThrow();
            tblCompraProduto tableProduto = createCompraProduto(compra, produto, item.getQuantidade(), item.getValorTotal());
            valorPedidos[0] = valorPedidos[0].add(tableProduto.getValorTotal());
        }
        dados.setValorTotal(valorPedidos[0]);
        return repository.save(dados);
    }

    public Optional<tblCompra> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblCompra> getByLocalEstoque(tblLocalEstoque localEstoque) {
        return repository.findByLocalEstoque(localEstoque);
    }

    public List<tblCompra> getBySituacaoCompra(tblSituacaoCompra situacaoCompra) {
        return repository.findBySituacaoCompra(situacaoCompra);
    }

    public List<tblCompra> getAll() {
        return repository.findAll();
    }

    public List<tblLocalEstoque> getAllLocalEstoque() {
        return localRepository.findByAtivoTrueOrderByDescricaoLocalAsc();
    }

    public List<tblSituacaoCompra> getAllSituacao() {
        return situacaoRepository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblCompra> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblCompra> getActiveEstoqueFalse() {
        return repository.findByAtivoTrueAndEstoqueFalse();
    }

    public List<tblCompra> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblCompra table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblCompra> table = repository.findById(id);
        return table.map(tblCompra::getAtivo).orElse(false);
    }
}