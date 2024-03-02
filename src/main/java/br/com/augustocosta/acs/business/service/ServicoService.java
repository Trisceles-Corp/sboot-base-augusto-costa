package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblServico;
import br.com.augustocosta.acs.persistence.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository repository;

    @Autowired
    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblServico create(tblServico table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblServico> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblServico> getByName(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblServico> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblServico> getAll() {
        return repository.findAll();
    }

    public List<tblServico> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblServico> getInactives() {
        return repository.findByAtivoFalse();
    }

    public tblServico update(Integer id, tblServico dados) {
        tblServico table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setNome(dados.getNome());
        table.setTempo(dados.getTempo());
        table.setObservacao(dados.getObservacao());
        table.setDesconto(dados.getDesconto());
        table.setComissao(dados.getComissao());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblServico table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblServico> table = repository.findById(id);
        return table.map(tblServico::getAtivo).orElse(false);
    }
}