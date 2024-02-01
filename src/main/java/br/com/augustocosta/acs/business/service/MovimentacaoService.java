package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblMovimentacao;
import br.com.augustocosta.acs.integration.entity.tblTipoMovimentacao;
import br.com.augustocosta.acs.integration.entity.tblVenda;
import br.com.augustocosta.acs.persistence.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository repository;

    @Autowired
    public MovimentacaoService(MovimentacaoRepository repository) {
        this.repository = repository;
    }

    public tblMovimentacao create(tblMovimentacao table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public List<tblMovimentacao> getAll() {
        return repository.findAll();
    }

    public Optional<tblMovimentacao> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblMovimentacao> getByNrNotaFiscal(Integer nrNotaFiscal) {
        return repository.findByNrNotaFiscal(nrNotaFiscal);
    }

    public List<tblMovimentacao> getByDataCriacao(LocalDateTime dataCriacao) {
        return repository.findByDataCriacao(dataCriacao);
    }

    public List<tblMovimentacao> getByTipoMovimentacao(tblTipoMovimentacao tipoMovimentacao) {
        return repository.findByTipoMovimentacao(tipoMovimentacao);
    }

    public List<tblMovimentacao> getByVenda(tblVenda venda) {
        return repository.findByVenda(venda);
    }

    public List<tblMovimentacao> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblMovimentacao> getInactives() {
        return repository.findByAtivoFalse();
    }

    public tblMovimentacao update(Integer id, tblMovimentacao dados) {
        tblMovimentacao table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimentação não encontrada com id: " + id));

        table.setTipoMovimentacao(dados.getTipoMovimentacao());
        table.setVenda(dados.getVenda());
        table.setObservacao(dados.getObservacao());
        table.setValorTotal(dados.getValorTotal());
        table.setNrNotaFiscal(dados.getNrNotaFiscal());
        table.setSerieNotaFiscal(dados.getSerieNotaFiscal());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    public void delete(Integer id, int alteradoPor) {
        tblMovimentacao table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimentação não encontrada com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblMovimentacao> table = repository.findById(id);
        return table.map(tblMovimentacao::getAtivo).orElse(false);
    }
}