package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblTipo;
import br.com.augustocosta.acs.persistence.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
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
    public tblTipo create(tblTipo table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblTipo> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblTipo> getByName(String nome) {
        return repository.findByDescricao(nome);
    }

    public List<tblTipo> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByDescricaoAsc();
    }

    public List<tblTipo> getAll() {
        return repository.findAll();
    }

    public List<tblTipo> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblTipo> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblTipo update(Integer id, tblTipo dados) {
        tblTipo table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo não encontrado com id: " + id));

        table.setDescricao(dados.getDescricao());
        table.setDescricao(dados.getDescricao());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblTipo table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblTipo> table = repository.findById(id);
        return table.map(tblTipo::getAtivo).orElse(false);
    }
}