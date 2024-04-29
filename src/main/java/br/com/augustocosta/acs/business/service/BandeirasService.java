package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblBandeiras;
import br.com.augustocosta.acs.persistence.repository.BandeirasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BandeirasService {

    private final BandeirasRepository repository;

    @Autowired
    public BandeirasService(BandeirasRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblBandeiras create(tblBandeiras table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblBandeiras> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblBandeiras> getAll() {
        return repository.findAll();
    }

    public List<tblBandeiras> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblBandeiras> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblBandeiras> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblBandeiras update(tblBandeiras dados) {
        return repository.save(dados);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblBandeiras table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Saida não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblBandeiras> table = repository.findById(id);
        return table.map(tblBandeiras::getAtivo).orElse(false);
    }
}