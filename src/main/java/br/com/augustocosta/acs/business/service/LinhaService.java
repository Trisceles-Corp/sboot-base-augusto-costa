package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblLinha;
import br.com.augustocosta.acs.persistence.repository.LinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LinhaService {

    private final LinhaRepository repository;

    @Autowired
    public LinhaService(LinhaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblLinha create(tblLinha table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblLinha> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblLinha> getByName(String nome) {
        return repository.findByDescricaoLinha(nome);
    }

    public List<tblLinha> getAll() {
        return repository.findAll();
    }

    public List<tblLinha> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblLinha> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblLinha update(Integer id, tblLinha dados) {
        tblLinha table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setDescricaoLinha(dados.getDescricaoLinha());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblLinha table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblLinha> table = repository.findById(id);
        return table.map(tblLinha::getAtivo).orElse(false);
    }
}