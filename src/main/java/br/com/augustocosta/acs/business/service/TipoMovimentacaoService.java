package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblTipoMovimentacao;
import br.com.augustocosta.acs.persistence.repository.TipoMovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public tblTipoMovimentacao salvar(tblTipoMovimentacao table) {
        return repository.save(table);
    }

    public Optional<tblTipoMovimentacao> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<tblTipoMovimentacao> buscarPorNome(String nome) {
        return repository.findByDescricaoMovimentacao(nome);
    }

    public List<tblTipoMovimentacao> listarTodos() {
        return repository.findAll();
    }

    public List<tblTipoMovimentacao> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<tblTipoMovimentacao> listarInativos() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblTipoMovimentacao atualizar(tblTipoMovimentacao table) {
        return repository.save(table);
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblTipoMovimentacao> table = repository.findById(id);
        return table.map(tblTipoMovimentacao::getAtivo).orElse(false);
    }
}