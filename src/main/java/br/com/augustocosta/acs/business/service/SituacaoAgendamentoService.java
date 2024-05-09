package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblSituacaoAgendamento;
import br.com.augustocosta.acs.persistence.repository.SituacaoAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SituacaoAgendamentoService {

    private final SituacaoAgendamentoRepository repository;

    @Autowired
    public SituacaoAgendamentoService(SituacaoAgendamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblSituacaoAgendamento create(tblSituacaoAgendamento table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblSituacaoAgendamento> getById(Integer id) {
        return repository.findById(id);
    }

    public tblSituacaoAgendamento getByName(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblSituacaoAgendamento> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblSituacaoAgendamento> getAll() {
        return repository.findAll();
    }

    public List<tblSituacaoAgendamento> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblSituacaoAgendamento> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblSituacaoAgendamento update(Integer id, tblSituacaoAgendamento dados) {
        tblSituacaoAgendamento table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Situação agendamento não encontrado com id: " + id));

        table.setNome(dados.getNome());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblSituacaoAgendamento table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Situação agendamento não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblSituacaoAgendamento> table = repository.findById(id);
        return table.map(tblSituacaoAgendamento::getAtivo).orElse(false);
    }
}