package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblCargo;
import br.com.augustocosta.acs.integration.entity.tblCategoria;
import br.com.augustocosta.acs.integration.entity.tblPerfil;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import br.com.augustocosta.acs.persistence.repository.CargoRepository;
import br.com.augustocosta.acs.persistence.repository.PerfilRepository;
import br.com.augustocosta.acs.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final CargoRepository cargoRepository;
    private final PerfilRepository perfilRepository;

    @Autowired
    public UsuarioService(UsuarioRepository repository, CargoRepository cargoRepository, PerfilRepository perfilRepository) {
        this.repository = repository;
        this.perfilRepository = perfilRepository;
        this.cargoRepository = cargoRepository;
    }

    @Transactional
    public tblUsuario create(tblUsuario table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public List<tblUsuario> getAll() {
        return repository.findAll();
    }

    public List<tblCargo> getAllActivesByCargo(){return cargoRepository.findByAtivoTrueOrderByNomeAsc(); }

    public List<tblPerfil> getAllActivesByPerfil(){return perfilRepository.findByAtivoTrueOrderByNomeAsc(); }

    public Optional<tblUsuario> getById(Integer id) {
        return repository.findById(id);
    }

    public tblUsuario getByUserId(Integer id) {
        return repository.getReferenceById(id);
    }

    public List<tblUsuario> getByName(String nome) {
        return repository.findByNomeOrderByNomeAsc(nome);
    }

    public List<tblUsuario> getByLastName(String sobrenome) {
        return repository.findBySobrenomeOrderByNomeAsc(sobrenome);
    }

    public List<tblUsuario> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblUsuario> getActiveByPerfilAsc(Integer perfilId) {
        return repository.findByPerfilId(perfilId);
    }

    public List<tblUsuario> getByCPF(String cpf) {
        return repository.findByCpfOrderByNomeAsc(cpf);
    }

    public List<tblUsuario> getByEmail(String email) {
        return repository.findByEmailOrderByNomeAsc(email);
    }

    public List<tblUsuario> getByCargo(tblCargo cargo) {
        return repository.findByCargoOrderByNomeAsc(cargo);
    }

    public List<tblUsuario> getByPerfil(tblPerfil perfil) {
        return repository.findByPerfilOrderByNomeAsc(perfil);
    }

    public List<tblUsuario> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblUsuario> getInactives() {
        return repository.findByAtivoFalse();
    }

    public tblUsuario update(Integer id, tblUsuario dados) {
        tblUsuario table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com id: " + id));

        table.setCpf(dados.getCpf());
        table.setNome(dados.getNome());
        table.setSobrenome(dados.getSobrenome());
        table.setGenero(dados.getGenero());
        table.setDataNascimento(dados.getDataNascimento());
        table.setEmail(dados.getEmail());
        table.setSenha(dados.getSenha());
        table.setEndereco(dados.getEndereco());
        table.setDdiCelular(dados.getDdiCelular());
        table.setDddCelular(dados.getDddCelular());
        table.setCelular(dados.getCelular());
        table.setDdiTelefone(dados.getDdiTelefone());
        table.setDddTelefone(dados.getDddTelefone());
        table.setTelefone(dados.getTelefone());
        table.setProfissao(dados.getProfissao());
        table.setObservacao(dados.getObservacao());
        table.setCargo(dados.getCargo());
        table.setPerfil(dados.getPerfil());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblUsuario table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblUsuario> table = repository.findById(id);
        return table.map(tblUsuario::getAtivo).orElse(false);
    }

    public boolean validateLogin(String email, String senha) {
        return repository.findByEmailOrderByNomeAsc(email)
                .stream()
                .anyMatch(user -> user.getSenha().equals(senha) && user.getAtivo());
    }
}