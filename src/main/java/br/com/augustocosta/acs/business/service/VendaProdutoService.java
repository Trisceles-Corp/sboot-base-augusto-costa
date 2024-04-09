package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.VendasProduto;
import br.com.augustocosta.acs.integration.entity.tblVendaProduto;
import br.com.augustocosta.acs.persistence.repository.VendaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VendaProdutoService {

    private final VendaProdutoRepository repository;

    @Autowired
    public VendaProdutoService(VendaProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblVendaProduto create(tblVendaProduto table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        return repository.save(table);
    }

    public Optional<tblVendaProduto> findById(Integer vendaId, Integer produtoId) {
        VendasProduto id = new VendasProduto(vendaId, produtoId);
        return repository.findById(id);
    }

    @Transactional
    public tblVendaProduto update(Integer vendaId, Integer produtoId, tblVendaProduto dados) {
        VendasProduto id = new VendasProduto(vendaId, produtoId);
        tblVendaProduto table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda Produtos não encontrado com id: " + id));

        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    public void delete(Integer vendaId, Integer produtoId) {
        VendasProduto id = new VendasProduto(vendaId, produtoId);
        repository.deleteById(id);
    }
}
