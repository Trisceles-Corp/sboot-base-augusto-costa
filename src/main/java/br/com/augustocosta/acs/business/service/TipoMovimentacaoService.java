package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblTipoMovimentacao;
import br.com.augustocosta.acs.persistence.repository.TipoMovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TipoMovimentacaoService {

    private final TipoMovimentacaoRepository repository;

    @Autowired
    public TipoMovimentacaoService(TipoMovimentacaoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblTipoMovimentacao create(tblTipoMovimentacao table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblTipoMovimentacao> getById(Integer id) {
        return repository.findById(id);
    }

    public tblTipoMovimentacao getByName(String nome) {
        return repository.findByDescricaoMovimentacao(nome);
    }

    public List<tblTipoMovimentacao> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByDescricaoMovimentacaoAsc();
    }

    public List<tblTipoMovimentacao> getAll() {
        return repository.findAll();
    }

    public List<tblTipoMovimentacao> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblTipoMovimentacao> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblTipoMovimentacao update(Integer id, tblTipoMovimentacao dados) {
        tblTipoMovimentacao table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo Movimentação não encontrado com id: " + id));

        table.setDescricaoMovimentacao(dados.getDescricaoMovimentacao());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblTipoMovimentacao table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo Movimentação não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblTipoMovimentacao> table = repository.findById(id);
        return table.map(tblTipoMovimentacao::getAtivo).orElse(false);
    }
}