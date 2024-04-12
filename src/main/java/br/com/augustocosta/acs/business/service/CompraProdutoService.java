package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblCompra;
import br.com.augustocosta.acs.integration.entity.tblCompraProduto;
import br.com.augustocosta.acs.integration.entity.tblProduto;
import br.com.augustocosta.acs.persistence.repository.CompraProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompraProdutoService {

    private final CompraProdutoRepository repository;

    @Autowired
    public CompraProdutoService(CompraProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblCompraProduto create(tblCompraProduto table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblCompraProduto> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblCompraProduto> getByProduto(tblProduto produto) {
        return repository.findByProduto(produto);
    }

    public List<tblCompraProduto> getByCompra(tblCompra compra) {
        return repository.findByCompra(compra);
    }

    public List<tblCompraProduto> getAll() {
        return repository.findAll();
    }

    public List<tblCompraProduto> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblCompraProduto> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblCompraProduto update(Integer id, tblCompraProduto dados) {
        tblCompraProduto table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblCompraProduto table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblCompraProduto> table = repository.findById(id);
        return table.map(tblCompraProduto::getAtivo).orElse(false);
    }
}