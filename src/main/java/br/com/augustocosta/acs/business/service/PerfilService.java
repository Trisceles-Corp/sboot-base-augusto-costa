package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblPerfil;
import br.com.augustocosta.acs.integration.entity.tblTipoPerfil;
import br.com.augustocosta.acs.persistence.repository.PerfilRepository;
import br.com.augustocosta.acs.persistence.repository.TipoPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    private final PerfilRepository repository;
    private final TipoPerfilRepository tipoPerfilRepository;

    @Autowired
    public PerfilService(PerfilRepository repository, TipoPerfilRepository tipoPerfilRepository) {
        this.repository = repository;
        this.tipoPerfilRepository = tipoPerfilRepository;
    }

    public tblPerfil create(tblPerfil table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public List<tblPerfil> getAll() {
        return repository.findAll();
    }

    public Optional<tblPerfil> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblPerfil> getByName(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblPerfil> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblTipoPerfil> getAllTipoPerfil() {
        return tipoPerfilRepository.findByAtivoTrueOrderByDescricaoAsc();
    }

    public List<tblPerfil> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblPerfil> getInactives() {
        return repository.findByAtivoFalse();
    }

    public tblPerfil update(Integer id, tblPerfil dados) {
        tblPerfil table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado com id: " + id));

        table.setNome(dados.getNome());
        table.setTipoPerfil(dados.getTipoPerfil());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());
        return repository.save(table);
    }

    public void delete(Integer id, int alteradoPor) {
        tblPerfil table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblPerfil> table = repository.findById(id);
        return table.map(tblPerfil::getAtivo).orElse(false);
    }

}