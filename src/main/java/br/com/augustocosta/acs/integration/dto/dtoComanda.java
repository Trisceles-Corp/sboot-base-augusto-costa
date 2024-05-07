package br.com.augustocosta.acs.integration.dto;

import br.com.augustocosta.acs.integration.entity.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class dtoComanda {

    private Integer comandaId;
    private Integer agendamentoId;
    private Integer caixaId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAgendamento;

    private LocalTime horaAgendamento;
    private Integer clienteId;
    private String nomeCliente;
    private Integer colaboradorId;
    private String nomeColaborador;
    private Double valorServicos;
    private Double valorProdutos;
    private Double valorDescontos;
    private Double valorComissao;
    private Double valorEncargos;
    private Double valorComanda;
    private Integer formaPagamentoId;
    private Integer bandeiraId;
    private Integer parcelas;
    private Double valorPagamento;
    private Double valorInserido;
    private Boolean situacao;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private Integer criadoPor;
    private Integer alteradoPor;
    private List<tblComandaPagamento> pagamentos;

    public dtoComanda() {
    }
}

