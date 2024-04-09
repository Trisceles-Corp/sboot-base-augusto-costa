package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblAgendamento;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import br.com.augustocosta.acs.integration.entity.tblServico;
import br.com.augustocosta.acs.integration.entity.tblBloqueio;
import br.com.augustocosta.acs.persistence.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDateTime;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    @Autowired
    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public tblAgendamento create(tblAgendamento table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public List<tblAgendamento> getAll() {
        return repository.findAll();
    }

    public Optional<tblAgendamento> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblAgendamento> getByCliente(tblUsuario cliente) {
        return repository.findByCliente(cliente);
    }

    public List<tblAgendamento> getByColaborador(tblUsuario colaborador) {
        return repository.findByColaborador(colaborador);
    }

    public List<tblAgendamento> getByBloqueio(tblBloqueio bloqueio) {
        return repository.findByBloqueio(bloqueio);
    }

    public List<tblAgendamento> getByDataAgendamento(Date dataAgendamento) {
        return repository.findByDataAgendamento(dataAgendamento);
    }

    public List<tblAgendamento> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblAgendamento> getInactives() {
        return repository.findByAtivoFalse();
    }

    public tblAgendamento update(Integer id, tblAgendamento dados) {
        tblAgendamento table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com id: " + id));

        table.setCliente(dados.getCliente());
        table.setColaborador(dados.getColaborador());
        table.setDataAgendamento(dados.getDataAgendamento());
        table.setHoraAgendamento(dados.getHoraAgendamento());
        table.setBloqueio(dados.getBloqueio());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    public void delete(Integer id, int alteradoPor) {
        tblAgendamento table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblAgendamento> table = repository.findById(id);
        return table.map(tblAgendamento::getAtivo).orElse(false);
    }
}