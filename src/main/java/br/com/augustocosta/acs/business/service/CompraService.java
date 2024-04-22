package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblCompra;
import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import br.com.augustocosta.acs.integration.entity.tblSituacaoCompra;
import br.com.augustocosta.acs.persistence.repository.CompraRepository;
import br.com.augustocosta.acs.persistence.repository.LocalEstoqueRepository;
import br.com.augustocosta.acs.persistence.repository.SituacaoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    private final CompraRepository repository;
    private final LocalEstoqueRepository localRepository;
    private final SituacaoCompraRepository situacaoRepository;

    @Autowired
    public CompraService(CompraRepository repository, LocalEstoqueRepository localRepository, SituacaoCompraRepository situacaoRepository) {
        this.repository = repository;
        this.localRepository = localRepository;
        this.situacaoRepository = situacaoRepository;
    }

    @Transactional
    public tblCompra create(tblCompra table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblCompra> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblCompra> getByLocalEstoque(tblLocalEstoque localEstoque) {
        return repository.findByLocalEstoque(localEstoque);
    }

    public List<tblCompra> getBySituacaoCompra(tblSituacaoCompra situacaoCompra) {
        return repository.findBySituacaoCompra(situacaoCompra);
    }

    public List<tblCompra> getAll() {
        return repository.findAll();
    }

    public List<tblLocalEstoque> getAllLocalEstoque() {
        return localRepository.findByAtivoTrueOrderByDescricaoLocalAsc();
    }

    public List<tblSituacaoCompra> getAllSituacao() {
        return situacaoRepository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblCompra> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblCompra> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblCompra update(tblCompra dados) {
        return repository.save(dados);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblCompra table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblCompra> table = repository.findById(id);
        return table.map(tblCompra::getAtivo).orElse(false);
    }
}