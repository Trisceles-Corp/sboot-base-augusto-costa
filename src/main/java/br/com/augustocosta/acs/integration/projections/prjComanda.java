package br.com.augustocosta.acs.integration.projections;

import br.com.augustocosta.acs.integration.entity.tblAgendamento;

import java.time.*;
import java.util.Date;

public interface prjComanda {

    Integer getComandaId();
    Integer getAgendamentoId();
    Date getDataAgendamento();
    LocalTime getHoraAgendamento();
    tblAgendamento getAgendamento();
    Integer getClienteId();
    String getNomeCliente();
    Integer getColaboradorId();
    String getNomeColaborador();
    Double getValorServicos();
    Double getValorProdutos();
    Double getValorDescontos();
    Double getValorComissao();
    Double getValorEncargos();
    Double getValorComanda();
    Boolean getSituacao();
    Boolean getAtivo();
    LocalDateTime getDataCriacao();
    LocalDateTime getDataAlteracao();
    Integer getCriadoPor();
    Integer getAlteradoPor();
}