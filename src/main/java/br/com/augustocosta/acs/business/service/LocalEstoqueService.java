package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import br.com.augustocosta.acs.persistence.repository.LocalEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LocalEstoqueService {

    private final LocalEstoqueRepository repository;

    @Autowired
    public LocalEstoqueService(LocalEstoqueRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblLocalEstoque salvar(tblLocalEstoque table) {
        return repository.save(table);
    }

    public Optional<tblLocalEstoque> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<tblLocalEstoque> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblLocalEstoque> listarTodos() {
        return repository.findAll();
    }

    public List<tblLocalEstoque> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<tblLocalEstoque> listarInativos() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblLocalEstoque atualizar(tblLocalEstoque table) {
        return repository.save(table);
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblLocalEstoque> table = repository.findById(id);
        return table.map(tblLocalEstoque::getAtivo).orElse(false);
    }
}