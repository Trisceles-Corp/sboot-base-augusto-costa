package br.com.augustocosta.acs.integration.projections;

import br.com.augustocosta.acs.integration.entity.tblAgendamento;

import java.math.BigDecimal;
import java.time.*;

public interface prjComanda {
    Integer getComandaId();
    Integer getAgendamentoId();
    LocalDate getDataAgendamento();
    LocalTime getHoraAgendamento();
    tblAgendamento getAgendamento();
    Integer getClienteId();
    String getNomeCliente();
    Integer getColaboradorId();
    String getNomeColaborador();
    BigDecimal getValorServicos();
    BigDecimal getValorProdutos();
    BigDecimal getValorDescontos();
    BigDecimal getValorComissao();
    BigDecimal getValorEncargos();
    BigDecimal getValorComanda();
    Boolean getSituacao();
    Boolean getAtivo();
    LocalDateTime getDataCriacao();
    LocalDateTime getDataAlteracao();
    Integer getCriadoPor();
    Integer getAlteradoPor();
}