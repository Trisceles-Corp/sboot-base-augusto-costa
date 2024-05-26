package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.dto.dtoGridAgendamento;
import br.com.augustocosta.acs.integration.entity.tblGridAgendamento;
import br.com.augustocosta.acs.integration.projections.prjGridAgendamento;
import br.com.augustocosta.acs.persistence.repository.GridAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GridAgendamentoService {

    @Autowired
    private GridAgendamentoRepository repository;

    public List<dtoGridAgendamento> getByGridAgendamento(LocalDate dataAgenda) {
        if (dataAgenda == null) {
            dataAgenda = LocalDate.now();
        }
        try {
            List<prjGridAgendamento> projections = repository.findByGridAgendamento(dataAgenda);
            return convertProjectionToDto(projections);
        } catch (JpaSystemException e) {
            return new ArrayList<>();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<tblGridAgendamento> getByDataAgendamento(LocalDate dataAgenda) {
        return repository.findByDataAgendamento(dataAgenda);
    }

    private List<dtoGridAgendamento> convertProjectionToDto(List<prjGridAgendamento> projections) throws SQLException {
        List<dtoGridAgendamento> dtos = new ArrayList<>();
        for (prjGridAgendamento projection : projections) {
            dtoGridAgendamento dto = new dtoGridAgendamento();
            dto.setHorario(projection.getHorario());
            dto.setColaboradores(projection.getColaboradores());
            dtos.add(dto);
        }
        return dtos;
    }
}