package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblTipoPerfil;
import br.com.augustocosta.acs.persistence.repository.TipoPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TipoPerfilService {

    private final TipoPerfilRepository repository;

    @Autowired
    public TipoPerfilService(TipoPerfilRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblTipoPerfil create(tblTipoPerfil table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblTipoPerfil> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblTipoPerfil> getByName(String nome) {
        return repository.findByDescricao(nome);
    }

    public List<tblTipoPerfil> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByDescricaoAsc();
    }

    public List<tblTipoPerfil> getAll() {
        return repository.findAll();
    }

    public List<tblTipoPerfil> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblTipoPerfil> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblTipoPerfil update(Integer id, tblTipoPerfil dados) {
        tblTipoPerfil table = repository.findById(id)
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
        tblTipoPerfil table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblTipoPerfil> table = repository.findById(id);
        return table.map(tblTipoPerfil::getAtivo).orElse(false);
    }
}