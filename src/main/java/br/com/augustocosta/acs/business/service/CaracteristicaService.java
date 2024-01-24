package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblCaracteristica;
import br.com.augustocosta.acs.persistence.repository.CaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CaracteristicaService {

    private final CaracteristicaRepository repository;

    @Autowired
    public CaracteristicaService(CaracteristicaRepository repository) {
        this.repository = repository;
    }

    public tblCaracteristica create(tblCaracteristica table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public List<tblCaracteristica> getAll() {
        return repository.findAll();
    }

    public Optional<tblCaracteristica> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblCaracteristica> getByDescricao(String descricao) {
        return repository.findByDescricaoCaracteristica(descricao);
    }

    public List<tblCaracteristica> getAtivas() {
        return repository.findByAtivoTrue();
    }

    public List<tblCaracteristica> getInativas() {
        return repository.findByAtivoFalse();
    }

    public tblCaracteristica update(Integer id, tblCaracteristica dados) {
        tblCaracteristica table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Característica não encontrada com id: " + id));

        table.setDescricaoCaracteristica(dados.getDescricaoCaracteristica());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    public void delete(Integer id, int alteradoPor) {
        tblCaracteristica table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Característica não encontrada com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }
}