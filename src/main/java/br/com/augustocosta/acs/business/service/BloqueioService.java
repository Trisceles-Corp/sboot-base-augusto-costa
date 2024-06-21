package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
public class BloqueioService {

    private final BloqueioRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ComandaRepository comandaRepository;
    private final SituacaoAgendamentoRepository situacaoAgendamentoRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final ServicoRepository servicoRepository;
    private final GridAgendamentoRepository gridAgendamentoRepository;

    @Autowired
    public BloqueioService(BloqueioRepository repository, UsuarioRepository usuarioRepository, ComandaRepository comandaRepository, SituacaoAgendamentoRepository situacaoAgendamentoRepository, AgendamentoRepository agendamentoRepository, ServicoRepository servicoRepository, GridAgendamentoRepository gridAgendamentoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.comandaRepository = comandaRepository;
        this.situacaoAgendamentoRepository = situacaoAgendamentoRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.servicoRepository = servicoRepository;
        this.gridAgendamentoRepository = gridAgendamentoRepository;
    }

    public List<tblBloqueio> getAll() {
        return repository.findAll();
    }

    public List<tblBloqueio> getActivesByUser(Integer id) {
        return repository.findByCriadoPorAndAtivoTrueOrderByDataBloqueioAsc(id);
    }

    public Optional<tblBloqueio> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblBloqueio> getByPeriodo(tblPeriodo periodo) {
        return repository.findByPeriodo(periodo);
    }

    public List<tblBloqueio> getByDiasSemana(tblDiasSemana diasSemana) {
        return repository.findByDiasSemana(diasSemana);
    }

    public List<tblBloqueio> getByDataBloqueio(Date dataBloqueio) {
        return repository.findByDataBloqueio(dataBloqueio);
    }

    public List<tblBloqueio> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblBloqueio> getInactives() {
        return repository.findByAtivoFalse();
    }

    @Transactional
    public tblBloqueio create(tblBloqueio table, Integer userId) {
        tblBloqueio bloqueio = createBloqueio(table, userId);
        tblAgendamento agendamento = createAgendamento(bloqueio, userId);
        tblComanda comanda = comandaRepository.findById(1).orElseThrow();
        tblServico servico = servicoRepository.findById(1).orElseThrow();

        // Cria grid bloqueio
        tblGridAgendamento grid = new tblGridAgendamento();
        grid.setComanda(comanda);
        grid.setBloqueio(bloqueio);
        grid.setAgendamento(agendamento);
        grid.setServico(servico);
        grid.setSituacao("Bloqueio");
        grid.setAtivo(true);
        grid.setDataCriacao(LocalDateTime.now());
        grid.setDataAlteracao(LocalDateTime.now());
        grid.setCriadoPor(userId);
        grid.setAlteradoPor(userId);
        gridAgendamentoRepository.save(grid);

        return bloqueio;
    }

    @Transactional
    public tblBloqueio update(tblBloqueio dados, Integer userId) {
        tblBloqueio table = repository.findById(dados.getId())
                .orElseThrow(() -> new IllegalArgumentException("Bloqueio não encontrado com id: " + dados.getId()));

        table.setPeriodo(dados.getPeriodo());
        table.setDiasSemana(dados.getDiasSemana());
        table.setDataBloqueio(dados.getDataBloqueio());
        table.setHoraInicial(dados.getHoraInicial());
        table.setHoraFinal(dados.getHoraFinal());
        table.setMotivoBloqueio(dados.getMotivoBloqueio());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(userId);

        tblGridAgendamento grid = gridAgendamentoRepository.findByBloqueio(table);
        if(grid != null){
            tblAgendamento agendamento = agendamentoRepository.findById(grid.getAgendamento().getId()).orElseThrow();
            agendamento.setHoraAgendamento(table.getHoraInicial());
            agendamento.setDataAlteracao(LocalDateTime.now());
            agendamento.setAlteradoPor(userId);
            agendamentoRepository.save(agendamento);
        }
        return repository.save(table);
    }

    @Transactional
    public void delete(Integer id, int alteradoPor) {
        tblBloqueio table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bloqueio não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblBloqueio> table = repository.findById(id);
        return table.map(tblBloqueio::getAtivo).orElse(false);
    }

    @Transactional
    public tblBloqueio createBloqueio(tblBloqueio table, Integer userId) {
        table.setAtivo(true);
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setCriadoPor(table.getCriadoPor());
        table.setAlteradoPor(userId);
        return repository.save(table);
    }

    @Transactional
    public tblAgendamento createAgendamento(tblBloqueio bloqueio, Integer userId) {
        tblAgendamento table = new tblAgendamento();
        tblUsuario cliente = usuarioRepository.findById(1).orElseThrow();
        tblUsuario colaborador = usuarioRepository.findById(bloqueio.getCriadoPor()).orElseThrow();
        tblSituacaoAgendamento situacaoAgendamento = situacaoAgendamentoRepository.findById(5).orElseThrow();
        Duration duracao = Duration.between(bloqueio.getHoraInicial(), bloqueio.getHoraFinal());

        table.setCliente(cliente);
        table.setColaborador(colaborador);
        table.setDataAgendamento(bloqueio.getDataBloqueio());
        table.setHoraAgendamento(bloqueio.getHoraInicial());
        table.setSituacao(situacaoAgendamento);
        table.setDuracao(LocalTime.MIDNIGHT.plus(duracao));
        table.setAtivo(true);
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setCriadoPor(userId);
        table.setAlteradoPor(userId);
        return agendamentoRepository.save(table);
    }
}