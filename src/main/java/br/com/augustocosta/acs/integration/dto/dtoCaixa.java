package br.com.augustocosta.acs.integration.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
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
    private Double valorAbertura;
    private Double credito;
    private Double debito;
    private Double dinheiro;
    private Double pix;
    private Double valorProvisorio;
    private Double valorFechamento;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private Integer criadoPor;
    private Integer alteradoPor;

    public dtoCaixa() {
    }
}

