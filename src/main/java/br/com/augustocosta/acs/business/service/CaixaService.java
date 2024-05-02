package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblBandeiras;
import br.com.augustocosta.acs.integration.entity.tblCaixa;
import br.com.augustocosta.acs.persistence.repository.CaixaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CaixaService {

    private final CaixaRepository repository;

    @Autowired
    public CaixaService(CaixaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblCaixa create(tblCaixa table) {
        table.setAtivo(true);
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        return repository.save(table);
    }

    @Transactional
    public tblCaixa getByCaixaId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Caixa não encontrada."));
    }

    public List<tblCaixa> getByName(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblCaixa> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblCaixa> getAll() {
        return repository.findAll();
    }

    public List<tblCaixa> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblCaixa> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblCaixa update(tblCaixa dados) {
        return repository.save(dados);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblCaixa table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblCaixa> table = repository.findById(id);
        return table.map(tblCaixa::getAtivo).orElse(false);
    }
}