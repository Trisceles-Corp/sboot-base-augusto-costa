package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblCargo;
import br.com.augustocosta.acs.persistence.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public tblCargo salvar(tblCargo table) {
        return repository.save(table);
    }

    public Optional<tblCargo> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<tblCargo> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblCargo> listarTodos() {
        return repository.findAll();
    }

    public List<tblCargo> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<tblCargo> listarInativos() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblCargo atualizar(tblCargo table) {
        return repository.save(table);
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblCargo> cargo = repository.findById(id);
        return cargo.map(tblCargo::getAtivo).orElse(false);
    }
}