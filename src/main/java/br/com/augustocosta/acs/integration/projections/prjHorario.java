package br.com.augustocosta.acs.integration.projections;

import java.time.*;

public interface prjHorario {
    Integer getHorarioId();
    LocalTime getHorario();
    Boolean getAtivo();
    LocalDateTime getDataCriacao();
    LocalDateTime getDataAlteracao();
    Integer getCriadoPor();
    Integer getAlteradoPor();
}
