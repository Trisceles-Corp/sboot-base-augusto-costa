package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.MovimentacaoProduto;
import br.com.augustocosta.acs.integration.entity.tblMovimentacaoProduto;
import br.com.augustocosta.acs.persistence.repository.MovimentacaoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MovimentacaoProdutoService {

    private final MovimentacaoProdutoRepository repository;

    @Autowired
    public MovimentacaoProdutoService(MovimentacaoProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblMovimentacaoProduto create(tblMovimentacaoProduto table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        return repository.save(table);
    }

    public Optional<tblMovimentacaoProduto> findById(Integer movimentacaoId, Integer produtoId) {
        MovimentacaoProduto id = new MovimentacaoProduto(movimentacaoId, produtoId);
        return repository.findById(id);
    }

    @Transactional
    public tblMovimentacaoProduto update(Integer movimentacaoId, Integer produtoId, tblMovimentacaoProduto dados) {
        MovimentacaoProduto id = new MovimentacaoProduto(movimentacaoId, produtoId);
        tblMovimentacaoProduto table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimentação Produto não encontrado com id: " + id));

        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    public void delete(Integer movimentacaoId, Integer produtoId) {
        MovimentacaoProduto id = new MovimentacaoProduto(movimentacaoId, produtoId);
        repository.deleteById(id);
    }
}
