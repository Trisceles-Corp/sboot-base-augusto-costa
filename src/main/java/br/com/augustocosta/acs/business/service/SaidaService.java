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
public class SaidaService {

    private final SaidaRepository repository;
    private final ProdutoRepository produtoRepository;
    private final SaidaProdutoRepository saidaProdutoRepository;

    @Autowired
    public SaidaService(SaidaRepository repository, ProdutoRepository produtoRepository, SaidaProdutoRepository saidaProdutoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
        this.saidaProdutoRepository = saidaProdutoRepository;
    }

    @Transactional
    public tblSaida create(tblSaida table, List<tblSaidaProduto> tableSaidas) {
        tblSaida saida = createSaida(table);
        final BigDecimal[] valorPedidos = {BigDecimal.valueOf(0)};
        tableSaidas.forEach(saidaProduto -> {
            tblProduto produto = produtoRepository.findById(saidaProduto.getProduto().getId()).orElseThrow();
            tblSaidaProduto tableProduto = createSaidaProduto(saida, produto, saidaProduto.getQuantidade(), saidaProduto.getValorTotal());
            valorPedidos[0] = valorPedidos[0].add(tableProduto.getValorTotal());
        });
        saida.setValorTotal(valorPedidos[0]);
        return repository.save(saida);
    }

    public Optional<tblSaida> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblSaida> getByLocalEstoque(tblLocalEstoque localEstoque) {
        return repository.findByLocalEstoque(localEstoque);
    }

    public List<tblSaida> getBySolicitante(tblUsuario solicitante) {
        return repository.findBySolicitante(solicitante);
    }

    public List<tblSaida> getAll() {
        return repository.findAll();
    }

    public List<tblSaida> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblSaida> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblSaida update(tblSaida dados, List<tblSaidaProduto> tableSaidas) {
        tblSaida saida = repository.findById(dados.getId()).orElseThrow();
        List<tblSaidaProduto> saidaProdutos = saidaProdutoRepository.findBySaida(saida);
        saidaProdutoRepository.deleteAll(saidaProdutos);
        final BigDecimal[] valorPedidos = {BigDecimal.valueOf(0)};

        for (tblSaidaProduto item : tableSaidas) {
            tblProduto produto = produtoRepository.findById(item.getProduto().getId()).orElseThrow();
            tblSaidaProduto tableProduto = createSaidaProduto(saida, produto, item.getQuantidade(), item.getValorTotal());
            valorPedidos[0] = valorPedidos[0].add(tableProduto.getValorTotal());
        }
        dados.setValorTotal(valorPedidos[0]);
        return repository.save(dados);
    }

    @Transactional
    public tblSaida createSaida(tblSaida table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    @Transactional
    public tblSaidaProduto createSaidaProduto(tblSaida saida, tblProduto produto, Integer quantidade, BigDecimal valorTotal) {
        SaidaProduto id = new SaidaProduto(saida.getId(), produto.getId());
        tblSaidaProduto tableProduto = new tblSaidaProduto();
        tableProduto.setId(id);
        tableProduto.setValorUnitario(produto.getValorVenda());
        tableProduto.setQuantidade(quantidade);
        tableProduto.setValorTotal(valorTotal);
        tableProduto.setAtivo(true);
        tableProduto.setDataCriacao(LocalDateTime.now());
        tableProduto.setDataAlteracao(LocalDateTime.now());
        tableProduto.setCriadoPor(saida.getCriadoPor());
        tableProduto.setAlteradoPor(saida.getAlteradoPor());
        return saidaProdutoRepository.save(tableProduto);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblSaida table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Saida não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblSaida> table = repository.findById(id);
        return table.map(tblSaida::getAtivo).orElse(false);
    }
}