package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import br.com.augustocosta.acs.persistence.repository.LocalEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LocalEstoqueService {

    private final LocalEstoqueRepository repository;

    @Autowired
    public LocalEstoqueService(LocalEstoqueRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblLocalEstoque create(tblLocalEstoque table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblLocalEstoque> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblLocalEstoque> getByName(String nome) {
        return repository.findByDescricaoLocal(nome);
    }

    public List<tblLocalEstoque> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByDescricaoLocalAsc();
    }

    public List<tblLocalEstoque> getAll() {
        return repository.findAll();
    }

    public List<tblLocalEstoque> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblLocalEstoque> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblLocalEstoque update(Integer id, tblLocalEstoque dados) {
        tblLocalEstoque table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setDescricaoLocal(dados.getDescricaoLocal());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblLocalEstoque table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblLocalEstoque> table = repository.findById(id);
        return table.map(tblLocalEstoque::getAtivo).orElse(false);
    }
}