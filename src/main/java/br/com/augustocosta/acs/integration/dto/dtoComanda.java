package br.com.augustocosta.acs.integration.dto;

import br.com.augustocosta.acs.integration.entity.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.*;
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
    private BigDecimal valorServicos;
    private BigDecimal valorProdutos;
    private BigDecimal valorDescontos;
    private BigDecimal valorComissao;
    private BigDecimal valorEncargos;
    private BigDecimal valorComanda;
    private Integer formaPagamentoId;
    private Integer bandeiraId;
    private Integer parcelas;
    private BigDecimal valorPagamento;
    private BigDecimal valorInserido;
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

