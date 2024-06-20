package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblMarca;
import br.com.augustocosta.acs.persistence.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.augustocosta.acs.business.util.StringUtil.cleanString;

@Service
public class MarcaService {

    private final MarcaRepository repository;

    @Autowired
    public MarcaService(MarcaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblMarca create(tblMarca table) {
        table.setDescricaoMarca(cleanString(table.getDescricaoMarca()));
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblMarca> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblMarca> getByName(String nome) {
        return repository.findByDescricaoMarca(nome);
    }

    public List<tblMarca> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByDescricaoMarcaAsc();
    }

    public List<tblMarca> getAll() {
        return repository.findAll();
    }

    public List<tblMarca> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblMarca> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblMarca update(Integer id, tblMarca dados) {
        tblMarca table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setDescricaoMarca(cleanString(dados.getDescricaoMarca()));
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblMarca table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblMarca> table = repository.findById(id);
        return table.map(tblMarca::getAtivo).orElse(false);
    }
}