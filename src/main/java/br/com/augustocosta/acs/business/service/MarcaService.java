package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblMarca;
import br.com.augustocosta.acs.persistence.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    private final MarcaRepository repository;

    @Autowired
    public MarcaService(MarcaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblMarca salvar(tblMarca table) {
        return repository.save(table);
    }

    public Optional<tblMarca> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<tblMarca> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblMarca> listarTodos() {
        return repository.findAll();
    }

    public List<tblMarca> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<tblMarca> listarInativos() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblMarca atualizar(tblMarca table) {
        return repository.save(table);
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblMarca> table = repository.findById(id);
        return table.map(tblMarca::getAtivo).orElse(false);
    }
}