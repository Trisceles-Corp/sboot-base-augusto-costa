package br.com.augustocosta.acs.integration.projections;

import java.math.BigDecimal;
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
    BigDecimal getValorAbertura();
    BigDecimal getCredito();
    BigDecimal getDebito();
    BigDecimal getDinheiro();
    BigDecimal getPix();
    BigDecimal getValorProvisorio();
    BigDecimal getValorFechamento();
    Boolean getAtivo();
    LocalDateTime getDataCriacao();
    LocalDateTime getDataAlteracao();
    Integer getCriadoPor();
    Integer getAlteradoPor();
}