package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.persistence.repository.ProdutoRepository;
import br.com.augustocosta.acs.persistence.repository.VendaProdutoRepository;
import br.com.augustocosta.acs.persistence.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VendaProdutoService {

    private final VendaProdutoRepository repository;
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public VendaProdutoService(VendaProdutoRepository repository, VendaRepository vendaRepository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public tblVendaProduto create(tblVendaProduto table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        return repository.save(table);
    }

    public tblVendaProduto getById(Integer vendaId, Integer produtoId) {
        VendaProduto id = new VendaProduto(vendaId, produtoId);
        return repository.getReferenceById(id);
    }

    public List<tblVendaProduto> getByProduto(Integer produtoId) {
        tblProduto produto = produtoRepository.getReferenceById(produtoId);
        return repository.findByProduto(produto);
    }

    public List<tblVendaProduto> getByVenda(Integer vendaId) {
        tblVenda venda = vendaRepository.getReferenceById(vendaId);
        return repository.findByVenda(venda);
    }

    @Transactional
    public tblVendaProduto update(Integer vendaId, Integer produtoId, tblVendaProduto dados) {
        VendaProduto id = new VendaProduto(vendaId, produtoId);
        tblVendaProduto table = repository.getReferenceById(id);

        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    public void delete(Integer vendaId, Integer produtoId) {
        VendaProduto id = new VendaProduto(vendaId, produtoId);
        repository.deleteById(id);
    }
}
