package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblSituacaoCompra;
import br.com.augustocosta.acs.persistence.repository.SituacaoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SituacaoCompraService {

    private final SituacaoCompraRepository repository;

    @Autowired
    public SituacaoCompraService(SituacaoCompraRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblSituacaoCompra create(tblSituacaoCompra table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblSituacaoCompra> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblSituacaoCompra> getByName(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblSituacaoCompra> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblSituacaoCompra> getAll() {
        return repository.findAll();
    }

    public List<tblSituacaoCompra> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblSituacaoCompra> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblSituacaoCompra update(Integer id, tblSituacaoCompra dados) {
        tblSituacaoCompra table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setNome(dados.getNome());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblSituacaoCompra table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblSituacaoCompra> table = repository.findById(id);
        return table.map(tblSituacaoCompra::getAtivo).orElse(false);
    }
}