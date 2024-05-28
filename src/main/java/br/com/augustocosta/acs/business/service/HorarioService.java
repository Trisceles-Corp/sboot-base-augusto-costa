package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblHorario;
import br.com.augustocosta.acs.persistence.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {

    private final HorarioRepository repository;

    @Autowired
    public HorarioService(HorarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblHorario create(tblHorario table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public List<tblHorario> getByHorario(LocalTime nome) {
        return repository.findByHorario(nome);
    }

    public List<tblHorario> getActiveByHorarioAsc() {
        return repository.findByAtivoTrueOrderByHorarioAsc();
    }

    public List<tblHorario> getAll() {
        return repository.findAll();
    }

    public List<tblHorario> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblHorario> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblHorario update(tblHorario dados) {
        tblHorario table = repository.findById(dados.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + dados.getId()));

        table.setHorario(dados.getHorario());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblHorario table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblHorario> table = repository.findById(id);
        return table.map(tblHorario::getAtivo).orElse(false);
    }
}