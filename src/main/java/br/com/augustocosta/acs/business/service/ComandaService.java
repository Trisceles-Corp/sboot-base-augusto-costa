package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblComanda;
import br.com.augustocosta.acs.integration.entity.tblAgendamento;
import br.com.augustocosta.acs.persistence.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComandaService {

    private final ComandaRepository repository;

    @Autowired
    public ComandaService(ComandaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblComanda create(tblComanda table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblComanda> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblComanda> getByAgendamento(tblAgendamento agendamento) {
        return repository.findByAgendamento(agendamento);
    }

    public List<tblComanda> getAll() {
        return repository.findAll();
    }

    public List<tblComanda> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblComanda> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblComanda update(tblComanda dados) {
        return repository.save(dados);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblComanda table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comanda não encontrada com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblComanda> table = repository.findById(id);
        return table.map(tblComanda::getAtivo).orElse(false);
    }
}