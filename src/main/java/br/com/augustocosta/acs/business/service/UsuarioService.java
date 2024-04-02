package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.dto.dtoUsuario;
import br.com.augustocosta.acs.integration.entity.tblCargo;
import br.com.augustocosta.acs.integration.entity.tblEndereco;
import br.com.augustocosta.acs.integration.entity.tblPerfil;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import br.com.augustocosta.acs.integration.projections.prjUsuario;
import br.com.augustocosta.acs.persistence.repository.CargoRepository;
import br.com.augustocosta.acs.persistence.repository.PerfilRepository;
import br.com.augustocosta.acs.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<tblUsuario> getAll() {
        return repository.findAll();
    }

    public List<tblCargo> getAllActivesByCargo(){return cargoRepository.findByAtivoTrueOrderByNomeAsc(); }

    public List<tblPerfil> getAllActivesByPerfil(){return perfilRepository.findByAtivoTrueOrderByNomeAsc(); }

    public Optional<tblUsuario> getById(Integer id) {
        return repository.findById(id);
    }

    public tblUsuario getUsuarioById(Integer id) {
        return repository.findUsuarioById(id);
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

    public List<dtoUsuario> getAllByPerfil(Integer perfilId) {
        List<prjUsuario> projections = repository.findUsersByPerfilId(perfilId);
        return convertProjectionToDto(projections);
    }

    public List<dtoUsuario> getByCpfCnpj(Integer perfilId, Double cpfCnpj) {
        List<prjUsuario> projections = repository.findUsersByPerfilIdAndCpfCnpj(perfilId, cpfCnpj);
        return convertProjectionToDto(projections);
    }

    public List<dtoUsuario> getByEmail(Integer perfilId, String email) {
        List<prjUsuario> projections = repository.findUsersByPerfilIdAndEmail(perfilId, email);
        return convertProjectionToDto(projections);
    }

    public List<dtoUsuario> getByNome(Integer perfilId, String nome) {
        List<prjUsuario> projections = repository.findUsersByPerfilIdAndNome(perfilId, nome);
        return convertProjectionToDto(projections);
    }

    public List<dtoUsuario> getBySobrenome(Integer perfilId, String sobrenome) {
        List<prjUsuario> projections = repository.findUsersByPerfilIdAndSobrenome(perfilId, sobrenome);
        return convertProjectionToDto(projections);
    }

    public List<dtoUsuario> getByCelular(Integer perfilId, Double celular) {
        List<prjUsuario> projections = repository.findUsersByPerfilIdAndCelular(perfilId, celular);
        return convertProjectionToDto(projections);
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

    @Transactional
    public tblUsuario create(tblUsuario table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setCriadoPor(1);
        table.setAlteradoPor(1);
        table.setAtivo(true);
        return repository.save(table);
    }

    @Transactional
    public dtoUsuario createDto(tblEndereco endereco, dtoUsuario dados) {
        tblUsuario usuario = new tblUsuario();
        tblCargo cargo = cargoRepository.findById(dados.getCargoId())
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + dados.getCargoId()));
        tblPerfil perfil = perfilRepository.findById(dados.getPerfilId())
                .orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado com id: " + dados.getPerfilId()));
        repository.save(convertVo(usuario, endereco, cargo, perfil, dados));
        return dados;
    }

    public tblUsuario update(Integer id, tblUsuario dados) {
        tblUsuario table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com id: " + id));

        table.setCpfCnpj(dados.getCpfCnpj());
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

    public dtoUsuario updateDto(tblEndereco endereco, dtoUsuario dados) {
        tblUsuario table = repository.findById(dados.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + dados.getUsuarioId()));
        tblCargo cargo = cargoRepository.findById(dados.getCargoId())
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + dados.getCargoId()));
        tblPerfil perfil = perfilRepository.findById(dados.getPerfilId())
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado com id: " + dados.getPerfilId()));
        repository.save(convertVo(table, endereco, cargo, perfil, dados));
        return dados;
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

    public tblUsuario convertVo(tblUsuario table, tblEndereco endereco, tblCargo cargo, tblPerfil perfil, dtoUsuario dados)
    {
        if (table.getId() != null && table.getId() != 0){
            table = repository.findById(table.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado."));
            table.setDataAlteracao(LocalDateTime.now());
        }
        else {
            table.setAtivo(true);
            table.setDataCriacao(LocalDateTime.now());
            table.setDataAlteracao(LocalDateTime.now());
            table.setCriadoPor(1);
            table.setAlteradoPor(1);
        }

        table.setCpfCnpj(dados.getCpfCnpj());
        table.setNome(dados.getNome());
        table.setSobrenome(dados.getSobrenome());
        table.setGenero(dados.getGenero());
        table.setDataNascimento(dados.getDataNascimento());
        table.setEmail(dados.getEmail());
        table.setSenha(dados.getSenha());
        table.setEndereco(endereco);
        table.setDdiCelular(dados.getDdiCelular());
        table.setDddCelular(dados.getDddCelular());
        table.setCelular(dados.getCelular());
        table.setDdiTelefone(dados.getDdiTelefone());
        table.setDddTelefone(dados.getDddTelefone());
        table.setTelefone(dados.getTelefone());
        table.setProfissao(dados.getProfissao());
        table.setObservacao(dados.getObservacao());
        table.setCargo(cargo);
        table.setPerfil(perfil);
       return table;
    }

    public List<dtoUsuario> convertProjectionToDto(List<prjUsuario> projections) {
        List<dtoUsuario> dtos = new ArrayList<>();
        for(prjUsuario prj : projections) {
            dtoUsuario dto = new dtoUsuario();
            dto.setUsuarioId(prj.getUsuarioId());
            dto.setCpfCnpj(prj.getCpfCnpj());
            dto.setNome(prj.getNome());
            dto.setSobrenome(prj.getSobrenome());
            dto.setNomeCompleto(prj.getNomeCompleto());
            dto.setGenero(prj.getGenero());
            dto.setGeneroDescricao(prj.getGeneroDescricao());
            dto.setDataNascimento(prj.getDataNascimento());
            dto.setEmail(prj.getEmail());
            dto.setSenha(prj.getSenha());
            dto.setEnderecoId(prj.getEnderecoId());
            dto.setCep(prj.getCep());
            dto.setLogradouro(prj.getLogradouro());
            dto.setNumero(prj.getNumero());
            dto.setComplemento(prj.getComplemento());
            dto.setBairro(prj.getBairro());
            dto.setCidade(prj.getCidade());
            dto.setUf(prj.getUf());
            dto.setDdiCelular(prj.getDdiCelular());
            dto.setDddCelular(prj.getDddCelular());
            dto.setCelular(prj.getCelular());
            dto.setCelularCompleto(prj.getCelularCompleto());
            dto.setDdiTelefone(prj.getDdiTelefone());
            dto.setDddTelefone(prj.getDddTelefone());
            dto.setTelefone(prj.getTelefone());
            dto.setTelefoneCompleto(prj.getTelefoneCompleto());
            dto.setProfissao(prj.getProfissao());
            dto.setObservacao(prj.getObservacao());
            dto.setCargoId(prj.getCargoId());
            dto.setCargoNome(prj.getCargoNome());
            dto.setPerfilId(prj.getPerfilId());
            dto.setPerfilNome(prj.getPerfilNome());
            dto.setAtivo(prj.getAtivo());
            dto.setDataAlteracao(prj.getDataAlteracao());
            dto.setAlteradoPor(prj.getAlteradoPor());
            dtos.add(dto);
        }
        return dtos;
    }
}