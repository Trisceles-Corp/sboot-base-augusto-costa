package br.com.augustocosta.acs.integration.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.*;

@Getter
@Setter
@ToString
public class dtoCaixa {
    private Integer caixaId;
    private String nome;
    private Integer nomeIndice;
    private Integer responsavelAbertura;
    private String email;
    private String nomeRespAbertura;
    private LocalDateTime dataAbertura;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    private LocalTime hora;
    private Integer responsavelFechamento;
    private String nomeRespFechamento;
    private LocalDateTime dataFechamento;
    private BigDecimal valorAbertura;
    private BigDecimal credito;
    private BigDecimal debito;
    private BigDecimal dinheiro;
    private BigDecimal pix;
    private BigDecimal valorProvisorio;
    private BigDecimal valorFechamento;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private Integer criadoPor;
    private Integer alteradoPor;

    public dtoCaixa() {
    }
}

