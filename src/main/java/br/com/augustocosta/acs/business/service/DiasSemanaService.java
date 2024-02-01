package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblDiasSemana;
import br.com.augustocosta.acs.persistence.repository.DiasSemanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DiasSemanaService {

    private final DiasSemanaRepository repository;

    @Autowired
    public DiasSemanaService(DiasSemanaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblDiasSemana create(tblDiasSemana table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblDiasSemana> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblDiasSemana> getByName(String nome) {
        return repository.findByDiasSemana(nome);
    }

    public List<tblDiasSemana> getAll() {
        return repository.findAll();
    }

    public List<tblDiasSemana> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblDiasSemana> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblDiasSemana update(Integer id, tblDiasSemana dados) {
        tblDiasSemana table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setDiasSemana(dados.getDiasSemana());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblDiasSemana table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblDiasSemana> table = repository.findById(id);
        return table.map(tblDiasSemana::getAtivo).orElse(false);
    }
}