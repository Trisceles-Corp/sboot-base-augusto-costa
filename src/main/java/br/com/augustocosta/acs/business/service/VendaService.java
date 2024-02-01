package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblVenda;
import br.com.augustocosta.acs.integration.entity.tblAgendamento;
import br.com.augustocosta.acs.persistence.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository repository;

    @Autowired
    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    public tblVenda create(tblVenda table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public List<tblVenda> getAll() {
        return repository.findAll();
    }

    public Optional<tblVenda> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblVenda> getByAgendamento(tblAgendamento agendamento) {
        return repository.findByAgendamento(agendamento);
    }

    public List<tblVenda> getByDataCriacao(LocalDateTime dataCriacao) {
        return repository.findByDataCriacao(dataCriacao);
    }

    public List<tblVenda> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblVenda> getInactives() {
        return repository.findByAtivoFalse();
    }

    public tblVenda update(Integer id, tblVenda dados) {
        tblVenda table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada com id: " + id));

        table.setAgendamento(dados.getAgendamento());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    public void delete(Integer id, int alteradoPor) {
        tblVenda table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblVenda> table = repository.findById(id);
        return table.map(tblVenda::getAtivo).orElse(false);
    }
}