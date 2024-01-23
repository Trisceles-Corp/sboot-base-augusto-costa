package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblLinha;
import br.com.augustocosta.acs.persistence.repository.LinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LinhaService {

    private final LinhaRepository repository;

    @Autowired
    public LinhaService(LinhaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblLinha salvar(tblLinha table) {
        return repository.save(table);
    }

    public Optional<tblLinha> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<tblLinha> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblLinha> listarTodos() {
        return repository.findAll();
    }

    public List<tblLinha> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<tblLinha> listarInativos() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblLinha atualizar(tblLinha table) {
        return repository.save(table);
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblLinha> table = repository.findById(id);
        return table.map(tblLinha::getAtivo).orElse(false);
    }
}