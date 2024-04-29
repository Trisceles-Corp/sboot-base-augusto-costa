package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import br.com.augustocosta.acs.integration.entity.tblSaida;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import br.com.augustocosta.acs.persistence.repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaidaService {

    private final SaidaRepository repository;

    @Autowired
    public SaidaService(SaidaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblSaida create(tblSaida table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblSaida> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblSaida> getByLocalEstoque(tblLocalEstoque localEstoque) {
        return repository.findByLocalEstoque(localEstoque);
    }

    public List<tblSaida> getBySolicitante(tblUsuario solicitante) {
        return repository.findBySolicitante(solicitante);
    }

    public List<tblSaida> getAll() {
        return repository.findAll();
    }

    public List<tblSaida> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblSaida> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblSaida update(tblSaida dados) {
        return repository.save(dados);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblSaida table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Saida não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblSaida> table = repository.findById(id);
        return table.map(tblSaida::getAtivo).orElse(false);
    }
}