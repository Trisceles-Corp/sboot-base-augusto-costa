package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblPermissoes;
import br.com.augustocosta.acs.persistence.repository.PermissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
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
    public tblPermissoes create(tblPermissoes table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblPermissoes> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblPermissoes> GetByName(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblPermissoes> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblPermissoes> getAll() {
        return repository.findAll();
    }

    public List<tblPermissoes> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblPermissoes> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblPermissoes update(Integer id, tblPermissoes dados) {
        tblPermissoes table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permissão não encontrada com id: " + id));

        table.setNome(dados.getNome());
        table.setDescricao(dados.getDescricao());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblPermissoes table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permissão não encontrada com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblPermissoes> table = repository.findById(id);
        return table.map(tblPermissoes::getAtivo).orElse(false);
    }
}