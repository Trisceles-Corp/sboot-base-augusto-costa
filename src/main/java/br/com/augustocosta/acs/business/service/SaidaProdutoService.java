package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblSaida;
import br.com.augustocosta.acs.integration.entity.tblSaidaProduto;
import br.com.augustocosta.acs.integration.entity.tblProduto;
import br.com.augustocosta.acs.persistence.repository.SaidaProdutoRepository;
import br.com.augustocosta.acs.persistence.repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaidaProdutoService {

    private final SaidaProdutoRepository repository;
    private final SaidaRepository saidaRepository;

    @Autowired
    public SaidaProdutoService(SaidaProdutoRepository repository, SaidaRepository saidaRepository) {
        this.repository = repository;
        this.saidaRepository = saidaRepository;
    }

    @Transactional
    public tblSaidaProduto create(tblSaidaProduto table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblSaidaProduto> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblSaidaProduto> getByProduto(tblProduto produto) {
        return repository.findByProduto(produto);
    }

    public List<tblSaidaProduto> getBySaida(Integer saidaId) {
        tblSaida saida = saidaRepository.getReferenceById(saidaId);
        return repository.findBySaida(saida);
    }

    public List<tblSaidaProduto> getAll() {
        return repository.findAll();
    }

    public List<tblSaidaProduto> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblSaidaProduto> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblSaidaProduto update(Integer id, tblSaidaProduto dados) {
        tblSaidaProduto table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblSaidaProduto table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblSaidaProduto> table = repository.findById(id);
        return table.map(tblSaidaProduto::getAtivo).orElse(false);
    }
}