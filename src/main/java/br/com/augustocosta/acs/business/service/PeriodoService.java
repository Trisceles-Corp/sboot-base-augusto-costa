package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblPeriodo;
import br.com.augustocosta.acs.persistence.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public tblPeriodo salvar(tblPeriodo table) {
        return repository.save(table);
    }

    public Optional<tblPeriodo> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<tblPeriodo> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblPeriodo> listarTodos() {
        return repository.findAll();
    }

    public List<tblPeriodo> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<tblPeriodo> listarInativos() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblPeriodo atualizar(tblPeriodo table) {
        return repository.save(table);
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblPeriodo> table = repository.findById(id);
        return table.map(tblPeriodo::getAtivo).orElse(false);
    }
}