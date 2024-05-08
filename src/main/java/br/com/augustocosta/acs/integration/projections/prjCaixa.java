package br.com.augustocosta.acs.integration.projections;

import java.time.*;

public interface prjCaixa {
    Integer getCaixaId();
    String getNome();
    Integer getNomeIndice();
    Integer getResponsavelAbertura();
    String getEmail();
    String getNomeRespAbertura();
    LocalDateTime getDataAbertura();
    LocalDate getData();
    LocalTime getHora();
    Integer getResponsavelFechamento();
    String getNomeRespFechamento();
    LocalDateTime getDataFechamento();
    Double getValorAbertura();
    Double getCredito();
    Double getDebito();
    Double getDinheiro();
    Double getPix();
    Double getValorProvisorio();
    Double getValorFechamento();
    Boolean getAtivo();
    LocalDateTime getDataCriacao();
    LocalDateTime getDataAlteracao();
    Integer getCriadoPor();
    Integer getAlteradoPor();
}