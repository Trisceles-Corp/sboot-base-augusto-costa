package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblBloqueio;
import br.com.augustocosta.acs.integration.entity.tblPeriodo;
import br.com.augustocosta.acs.integration.entity.tblDiasSemana;
import br.com.augustocosta.acs.persistence.repository.BloqueioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
public class BloqueioService {

    private final BloqueioRepository repository;

    @Autowired
    public BloqueioService(BloqueioRepository repository) {
        this.repository = repository;
    }

    public tblBloqueio create(tblBloqueio table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public List<tblBloqueio> getAll() {
        return repository.findAll();
    }

    public Optional<tblBloqueio> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblBloqueio> getByPeriodo(tblPeriodo periodo) {
        return repository.findByPeriodo(periodo);
    }

    public List<tblBloqueio> getByDiasSemana(tblDiasSemana diasSemana) {
        return repository.findByDiasSemana(diasSemana);
    }

    public List<tblBloqueio> getByDataBloqueio(Date dataBloqueio) {
        return repository.findByDataBloqueio(dataBloqueio);
    }

    public List<tblBloqueio> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblBloqueio> getInactives() {
        return repository.findByAtivoFalse();
    }

    public tblBloqueio update(Integer id, tblBloqueio dados) {
        tblBloqueio table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bloqueio não encontrado com id: " + id));

        table.setPeriodo(dados.getPeriodo());
        table.setDiasSemana(dados.getDiasSemana());
        table.setDataBloqueio(dados.getDataBloqueio());
        table.setHoraInicial(dados.getHoraInicial());
        table.setHoraFinal(dados.getHoraFinal());
        table.setMotivoBloqueio(dados.getMotivoBloqueio());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    public void delete(Integer id, int alteradoPor) {
        tblBloqueio table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bloqueio não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblBloqueio> table = repository.findById(id);
        return table.map(tblBloqueio::getAtivo).orElse(false);
    }
}