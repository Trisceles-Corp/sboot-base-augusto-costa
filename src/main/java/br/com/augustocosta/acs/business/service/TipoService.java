package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblTipo;
import br.com.augustocosta.acs.persistence.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TipoService {

    private final TipoRepository repository;

    @Autowired
    public TipoService(TipoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblTipo salvar(tblTipo table) {
        return repository.save(table);
    }

    public Optional<tblTipo> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<tblTipo> buscarPorNome(String nome) {
        return repository.findByDescricao(nome);
    }

    public List<tblTipo> listarTodos() {
        return repository.findAll();
    }

    public List<tblTipo> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<tblTipo> listarInativos() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblTipo atualizar(tblTipo table) {
        return repository.save(table);
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblTipo> table = repository.findById(id);
        return table.map(tblTipo::getAtivo).orElse(false);
    }
}