package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblEndereco;
import br.com.augustocosta.acs.persistence.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;

    @Autowired
    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public tblEndereco create(tblEndereco table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public Optional<tblEndereco> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblEndereco> getByCep(String cep) {
        return repository.findByCep(cep);
    }

    public List<tblEndereco> getByName(String logradouro) {
        return repository.findByLogradouro(logradouro);
    }

    public List<tblEndereco> getByBairro(String nome) {
        return repository.findByBairro(nome);
    }

    public List<tblEndereco> getByCidade(String nome) {
        return repository.findByCidade(nome);
    }

    public List<tblEndereco> getByUF(String nome) {
        return repository.findByUf(nome);
    }

    public List<tblEndereco> getAll() {
        return repository.findAll();
    }

    public List<tblEndereco> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByLogradouroAsc();
    }

    public List<tblEndereco> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblEndereco> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblEndereco update(Integer id, tblEndereco dados) {
        tblEndereco table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado com id: " + id));

        table.setCep(dados.getCep());
        table.setLogradouro(dados.getLogradouro());
        table.setNumero(dados.getNumero());
        table.setComplemento(dados.getComplemento());
        table.setBairro(dados.getBairro());
        table.setCidade(dados.getCidade());
        table.setUf(dados.getUf());
        table.setObservacao(dados.getObservacao());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblEndereco table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblEndereco> table = repository.findById(id);
        return table.map(tblEndereco::getAtivo).orElse(false);
    }
}