package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.dto.dtoGridAgendamento;
import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.integration.projections.prjGridAgendamento;
import br.com.augustocosta.acs.persistence.repository.GridAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GridAgendamentoService {

    @Autowired
    private GridAgendamentoRepository repository;

    @Autowired
    private ServicosAgendamentoService servicosAgendamentoService;

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ComandaService comandaService;

    @Autowired
    private ServicoService servicoService;

    public List<dtoGridAgendamento> getByGridAgendamento(LocalDate dataAgenda) {
        if (dataAgenda == null) {
            dataAgenda = LocalDate.now();
        }
        try {
            List<prjGridAgendamento> projection = repository.findByGridAgendamento(dataAgenda);
            if(projection.size() <= 1){
                return new ArrayList<>();
            }
            return convertProjectionToDto(projection);
        } catch (JpaSystemException e) {
            return new ArrayList<>();
        }
    }

    private List<dtoGridAgendamento> convertProjectionToDto(List<prjGridAgendamento> rows) {
        List<dtoGridAgendamento> dtos = new ArrayList<>();
        for (prjGridAgendamento row : rows) {
            List<tblServico> servicosAgendamento = servicosAgendamentoService.getServicoByAgendamentoId(row.getAgendamentoId());
            LocalTime tempoTotalServicos = LocalTime.parse("00:00");

            for (tblServico servico : servicosAgendamento) {
                LocalTime tempoServico = servico.getTempo();
                tempoTotalServicos = tempoTotalServicos.plusHours(tempoServico.getHour())
                        .plusMinutes(tempoServico.getMinute());
            }

            tblAgendamento agendamento = agendamentoService.getById(row.getAgendamentoId()).orElseThrow();
            tblComanda comanda = comandaService.getById(row.getComandaId()).orElseThrow();
            tblServico servico = servicoService.getById(row.getServicoId()).orElseThrow();

            LocalTime horarioInicial = agendamento.getHoraAgendamento();
            LocalTime horarioFinal = horarioInicial.plusHours(tempoTotalServicos.getHour())
                    .plusMinutes(tempoTotalServicos.getMinute());

            dtoGridAgendamento dto = new dtoGridAgendamento();
            dto.setId(row.getGridAgendamentoId());
            dto.setHorarioIncial(horarioInicial);
            dto.setHorarioFinal(horarioFinal);
            dto.setComanda(comanda);
            dto.setAgendamento(agendamento);
            dto.setServico(servico);
            dto.setColaborador(agendamento.getColaborador().getNome());
            dto.setSituacao(row.getSituacao());
            dto.setAtivo(row.getAtivo());
            dto.setDataCriacao(row.getDataCriacao());
            dto.setDataAlteracao(row.getDataAlteracao());
            dto.setCriadoPor(row.getCriadoPor());
            dto.setAlteradoPor(row.getAlteradoPor());
            dtos.add(dto);
        }
        return dtos;
    }

//    public List<dtoGridAgendamento> getByGridAgendamento(LocalDate dataAgenda) {
//        if (dataAgenda == null) {
//            dataAgenda = LocalDate.now();
//        }
//        try {
//            List<prjGridAgendamento> projections = repository.findByGridAgendamento(dataAgenda);
//            return convertProjectionToDto(projections);
//        } catch (JpaSystemException e) {
//            return new ArrayList<>();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private List<dtoGridAgendamento> convertProjectionToDto(List<prjGridAgendamento> projections) throws SQLException {
//        List<dtoGridAgendamento> dtos = new ArrayList<>();
//        for (prjGridAgendamento projection : projections) {
//            dtoGridAgendamento dto = new dtoGridAgendamento();
//            dto.setHorario(projection.getHorario());
//            dto.setColaboradores(projection.getColaboradores());
//            dtos.add(dto);
//        }
//        return dtos;
//    }

}