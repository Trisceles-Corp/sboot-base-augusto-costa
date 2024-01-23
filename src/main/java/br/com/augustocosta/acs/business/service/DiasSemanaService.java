package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblDiasSemana;
import br.com.augustocosta.acs.persistence.repository.DiasSemanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DiasSemanaService {

    private final DiasSemanaRepository repository;

    @Autowired
    public DiasSemanaService(DiasSemanaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblDiasSemana salvar(tblDiasSemana table) {
        return repository.save(table);
    }

    public Optional<tblDiasSemana> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<tblDiasSemana> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblDiasSemana> listarTodos() {
        return repository.findAll();
    }

    public List<tblDiasSemana> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<tblDiasSemana> listarInativos() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblDiasSemana atualizar(tblDiasSemana table) {
        return repository.save(table);
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblDiasSemana> table = repository.findById(id);
        return table.map(tblDiasSemana::getAtivo).orElse(false);
    }
}