package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblCargo;
import br.com.augustocosta.acs.persistence.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    private final CargoRepository repository;

    @Autowired
    public CargoService(CargoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblCargo create(tblCargo table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblCargo> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblCargo> getByName(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblCargo> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblCargo> getAll() {
        return repository.findAll();
    }

    public List<tblCargo> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblCargo> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblCargo update(Integer id, tblCargo dados) {
        tblCargo table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setNome(dados.getNome());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblCargo table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblCargo> cargo = repository.findById(id);
        return cargo.map(tblCargo::getAtivo).orElse(false);
    }
}