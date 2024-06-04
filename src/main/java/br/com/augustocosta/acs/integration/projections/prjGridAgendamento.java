package br.com.augustocosta.acs.integration.projections;

import java.time.LocalDate;

public interface prjGridAgendamento {
    Long getGridAgendamentoId();
    Integer getComandaId();
    Integer getAgendamentoId();
    Integer getServicoId();
    String getSituacao();
    Boolean getAtivo();
    LocalDate getDataCriacao();
    LocalDate getDataAlteracao();
    Integer getCriadoPor();
    Integer getAlteradoPor();
}