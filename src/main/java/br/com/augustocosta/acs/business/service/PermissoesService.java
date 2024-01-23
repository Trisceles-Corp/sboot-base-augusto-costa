package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblPermissoes;
import br.com.augustocosta.acs.persistence.repository.PermissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PermissoesService {

    private final PermissoesRepository repository;

    @Autowired
    public PermissoesService(PermissoesRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblPermissoes salvar(tblPermissoes table) {
        return repository.save(table);
    }

    public Optional<tblPermissoes> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<tblPermissoes> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblPermissoes> listarTodos() {
        return repository.findAll();
    }

    public List<tblPermissoes> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<tblPermissoes> listarInativos() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblPermissoes atualizar(tblPermissoes table) {
        return repository.save(table);
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblPermissoes> table = repository.findById(id);
        return table.map(tblPermissoes::getAtivo).orElse(false);
    }
}