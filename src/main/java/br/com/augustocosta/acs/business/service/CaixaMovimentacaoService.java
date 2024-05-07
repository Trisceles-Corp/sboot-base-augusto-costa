package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.persistence.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CaixaMovimentacaoService {

    private final CaixaMovimentacaoRepository repository;
    private final CaixaRepository caixaRepository;
    private final ComandaRepository comandaRepository;
    private final TipoMovimentacaoRepository tipoMovimentacaoRepository;
    private final FormasPagamentoRepository formasPagamentoRepository;

    @Autowired
    public CaixaMovimentacaoService(CaixaMovimentacaoRepository repository, CaixaRepository caixaRepository, ComandaRepository comandaRepository, TipoMovimentacaoRepository tipoMovimentacaoRepository, FormasPagamentoRepository formasPagamentoRepository) {
        this.repository = repository;
        this.caixaRepository = caixaRepository;
        this.comandaRepository = comandaRepository;
        this.tipoMovimentacaoRepository = tipoMovimentacaoRepository;
        this.formasPagamentoRepository = formasPagamentoRepository;
    }

    @Transactional
    public tblCaixaMovimentacao create(tblCaixaMovimentacao table) {
        table.setAtivo(true);
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        return repository.save(table);
    }

    @Transactional
    public tblCaixaMovimentacao getByCaixaMovimentacaoId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Caixa movimentação não encontrada."));
    }

    public List<tblCaixa> getActiveCaixaByNameAsc() {
        return caixaRepository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblComanda> getComandaAll() {
        return comandaRepository.findAll();
    }

    public List<tblTipoMovimentacao> getActiveTipoMovimentacaoByNameAsc() {
        return tipoMovimentacaoRepository.findByAtivoTrueOrderByDescricaoMovimentacaoAsc();
    }

    public List<tblFormasPagamento> getActiveFormasPagamentoByNameAsc() {
        return formasPagamentoRepository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblCaixaMovimentacao> getAll() {
        return repository.findAll();
    }

    public List<tblCaixaMovimentacao> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblCaixaMovimentacao> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblCaixaMovimentacao update(Integer id, tblCaixaMovimentacao dados) {
        tblCaixaMovimentacao table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Caixa movimentação não encontrado com id: " + id));

        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblCaixaMovimentacao table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Caixa movimentação não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblCaixaMovimentacao> table = repository.findById(id);
        return table.map(tblCaixaMovimentacao::getAtivo).orElse(false);
    }
}