package br.com.augustocosta.acs.integration.projections;

import java.time.LocalDate;

public interface prjGridAgendamento {
    Long getGridAgendamentoId();
    Integer getComandaId();
    Integer getAgendametoId();
    Integer getServicoId();
    Boolean getAtivo();
    LocalDate getDataCriacao();
    LocalDate getDataAlteracao();
    Integer getCriadoPor();
    Integer getAlteradoPor();
}