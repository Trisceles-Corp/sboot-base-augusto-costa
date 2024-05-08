package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.dto.dtoCaixa;
import br.com.augustocosta.acs.integration.entity.tblCaixa;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import br.com.augustocosta.acs.integration.projections.prjCaixa;
import br.com.augustocosta.acs.persistence.repository.CaixaRepository;
import br.com.augustocosta.acs.persistence.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CaixaService {

    private final CaixaRepository repository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CaixaService(CaixaRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public tblCaixa findCaixaById(Integer id) {
        return entityManager.find(tblCaixa.class, id);
    }

    @Transactional
    public tblCaixa create(dtoCaixa dados) {
        tblCaixa caixa = new tblCaixa();
        tblUsuario usuarioAbertura = usuarioRepository.findById(dados.getResponsavelAbertura()).orElseThrow(() -> new RuntimeException("Caixa não encontrado"));
        Integer maxCaixaIndice = repository.findMaxCaixaIndiceForToday();
        if (maxCaixaIndice == 0) { maxCaixaIndice = 1; } else { maxCaixaIndice += 1; }
        String dataFormatada = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String indiceFormatado = String.format("%02d", maxCaixaIndice);
        String nomeCaixa = dataFormatada + indiceFormatado;

        caixa.setNome(nomeCaixa);
        caixa.setNomeIndice(maxCaixaIndice);
        caixa.setResponsavelAbertura(usuarioAbertura);
        caixa.setDataAbertura(LocalDateTime.now());
        caixa.setValorAbertura(dados.getValorAbertura());
        caixa.setAtivo(true);
        caixa.setDataCriacao(LocalDateTime.now());
        caixa.setDataAlteracao(LocalDateTime.now());
        caixa.setCriadoPor(dados.getResponsavelAbertura());
        caixa.setAlteradoPor(dados.getResponsavelAbertura());
        return repository.save(caixa);
    }

    @Transactional
    public tblCaixa getByCaixaId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Caixa não encontrada."));
    }

    public List<tblCaixa> getByName(String nome) {
        return repository.findByNome(nome);
    }

    public List<tblCaixa> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByNomeAsc();
    }

    public List<tblCaixa> getAll() {
        return repository.findAll();
    }

    public List<dtoCaixa> getAllByAtivo() {
        List<prjCaixa> projections = repository.findCaixasByAtivo();
        return convertProjectionToDto(projections);
    }

    public List<tblCaixa> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblCaixa> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblCaixa update(dtoCaixa dados) {
        tblUsuario usuarioAbertura = usuarioRepository.findById(dados.getResponsavelAbertura()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        tblUsuario usuarioFechamento = usuarioRepository.findById(dados.getResponsavelFechamento()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        tblCaixa caixa = new tblCaixa();
        caixa.setId(dados.getCaixaId());
        caixa.setNome(dados.getNome());
        caixa.setNomeIndice(dados.getNomeIndice());
        caixa.setResponsavelAbertura(usuarioAbertura);
        caixa.setDataAbertura(dados.getDataAbertura());
        caixa.setResponsavelFechamento(usuarioFechamento);
        caixa.setDataFechamento(LocalDateTime.now());
        caixa.setValorAbertura(dados.getValorAbertura());
        caixa.setValorFechamento(dados.getValorProvisorio());
        caixa.setAtivo(false);
        caixa.setDataAlteracao(LocalDateTime.now());
        caixa.setDataCriacao(dados.getDataCriacao());
        caixa.setAlteradoPor(dados.getResponsavelFechamento());
        caixa.setCriadoPor(dados.getCriadoPor());
        return repository.save(caixa);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblCaixa table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cargo não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblCaixa> table = repository.findById(id);
        return table.map(tblCaixa::getAtivo).orElse(false);
    }

    public List<dtoCaixa> convertProjectionToDto(List<prjCaixa> projections) {
        List<dtoCaixa> dtos = new ArrayList<>();
        for(prjCaixa prj : projections) {
            dtoCaixa dto = new dtoCaixa();
            dto.setCaixaId(prj.getCaixaId());
            dto.setNome(prj.getNome());
            dto.setNomeIndice(prj.getNomeIndice());
            dto.setResponsavelAbertura(prj.getResponsavelAbertura());
            dto.setEmail(prj.getEmail());
            dto.setNomeRespAbertura(prj.getNomeRespAbertura());
            dto.setDataAbertura(prj.getDataAbertura());
            dto.setData(prj.getData());
            dto.setHora(prj.getHora());
            dto.setResponsavelFechamento(prj.getResponsavelFechamento());
            dto.setNomeRespFechamento(prj.getNomeRespFechamento());
            dto.setDataFechamento(prj.getDataFechamento());
            dto.setValorAbertura(prj.getValorAbertura());
            dto.setCredito(prj.getCredito());
            dto.setDebito(prj.getDebito());
            dto.setDinheiro(prj.getDinheiro());
            dto.setPix(prj.getPix());
            dto.setValorProvisorio(prj.getValorProvisorio());
            dto.setValorFechamento(prj.getValorFechamento());
            dto.setAtivo(prj.getAtivo());
            dto.setDataCriacao(prj.getDataCriacao());
            dto.setCriadoPor(prj.getCriadoPor());
            dto.setDataAlteracao(prj.getDataAlteracao());
            dto.setAlteradoPor(prj.getAlteradoPor());
            dtos.add(dto);
        }
        return dtos;
    }
}