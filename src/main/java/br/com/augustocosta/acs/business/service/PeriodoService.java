package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblPeriodo;
import br.com.augustocosta.acs.persistence.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PeriodoService {

    private final PeriodoRepository repository;

    @Autowired
    public PeriodoService(PeriodoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblPeriodo create(tblPeriodo table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblPeriodo> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblPeriodo> getByName(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblPeriodo> getAll() {
        return repository.findAll();
    }

    public List<tblPeriodo> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblPeriodo> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblPeriodo update(Integer id, tblPeriodo dados) {
        tblPeriodo table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setNome(dados.getNome());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblPeriodo table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Periodo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblPeriodo> table = repository.findById(id);
        return table.map(tblPeriodo::getAtivo).orElse(false);
    }
}