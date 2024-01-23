package br.com.augustocosta.acs.business.service;


import br.com.augustocosta.acs.integration.entity.tblServico;
import br.com.augustocosta.acs.persistence.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public tblServico salvar(tblServico table) {
        return repository.save(table);
    }

    public Optional<tblServico> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<tblServico> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblServico> listarTodos() {
        return repository.findAll();
    }

    public List<tblServico> listarAtivos() {
        return repository.findByAtivoTrue();
    }

    public List<tblServico> listarInativos() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblServico atualizar(tblServico table) {
        return repository.save(table);
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblServico> table = repository.findById(id);
        return table.map(tblServico::getAtivo).orElse(false);
    }
}