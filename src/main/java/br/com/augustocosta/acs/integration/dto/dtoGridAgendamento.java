package br.com.augustocosta.acs.integration.dto;

import br.com.augustocosta.acs.integration.entity.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
public class dtoGridAgendamento {
    private Long id;
    private LocalTime horarioIncial;
    private LocalTime horarioFinal;
    private String colaborador;
    private tblComanda comanda;
    private tblAgendamento agendamento;
    private tblServico servico;
    private String situacao;
    private Boolean ativo;
    private LocalDate dataCriacao;
    private LocalDate dataAlteracao;
    private Integer criadoPor;
    private Integer alteradoPor;

    public dtoGridAgendamento() {
    }
}