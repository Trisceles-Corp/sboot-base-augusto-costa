package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblCategoria;
import br.com.augustocosta.acs.persistence.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblCategoria create(tblCategoria table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblCategoria> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblCategoria> getByName(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblCategoria> getAll() {
        return repository.findAll();
    }

    public List<tblCategoria> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblCategoria> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblCategoria> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblCategoria update(Integer id, tblCategoria dados) {
        tblCategoria table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrado com id: " + id));

        table.setNome(dados.getNome());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id) {
        tblCategoria table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(1);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblCategoria> table = repository.findById(id);
        return table.map(tblCategoria::getAtivo).orElse(false);
    }
}