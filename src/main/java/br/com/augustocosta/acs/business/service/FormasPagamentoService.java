package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblFormasPagamento;
import br.com.augustocosta.acs.persistence.repository.FormasPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FormasPagamentoService {

    private final FormasPagamentoRepository repository;

    @Autowired
    public FormasPagamentoService(FormasPagamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblFormasPagamento create(tblFormasPagamento table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblFormasPagamento> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblFormasPagamento> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblFormasPagamento> getAll() {
        return repository.findAll();
    }

    public List<tblFormasPagamento> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblFormasPagamento> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblFormasPagamento update(tblFormasPagamento dados) {
        return repository.save(dados);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblFormasPagamento table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Saida não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblFormasPagamento> table = repository.findById(id);
        return table.map(tblFormasPagamento::getAtivo).orElse(false);
    }
}