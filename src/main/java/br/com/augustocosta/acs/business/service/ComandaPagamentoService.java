package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblComanda;
import br.com.augustocosta.acs.integration.entity.tblComandaPagamento;
import br.com.augustocosta.acs.integration.entity.tblFormasPagamento;
import br.com.augustocosta.acs.persistence.repository.ComandaPagamentoRepository;
import br.com.augustocosta.acs.persistence.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComandaPagamentoService {

    private final ComandaPagamentoRepository repository;
    private final ComandaRepository comandaRepository;

    @Autowired
    public ComandaPagamentoService(ComandaPagamentoRepository repository, ComandaRepository comandaRepository) {
        this.repository = repository;
        this.comandaRepository = comandaRepository;
    }

    @Transactional
    public tblComandaPagamento create(tblComandaPagamento table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblComandaPagamento> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblComandaPagamento> getByForma(tblFormasPagamento forma) {
        return repository.findByFormaPagamento(forma);
    }

    public List<tblComandaPagamento> getByComanda(Integer comandaId) {
        tblComanda comanda = comandaRepository.getReferenceById(comandaId);
        return repository.findByComanda(comanda);
    }

    public List<tblComandaPagamento> getAll() {
        return repository.findAll();
    }

    public List<tblComandaPagamento> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblComandaPagamento> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblComandaPagamento update(Integer id, tblComandaPagamento dados) {
        tblComandaPagamento table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblComandaPagamento table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblComandaPagamento> table = repository.findById(id);
        return table.map(tblComandaPagamento::getAtivo).orElse(false);
    }
}