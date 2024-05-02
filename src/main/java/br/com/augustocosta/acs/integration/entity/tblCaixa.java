package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_caixa")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CaixaId")
    private Integer id;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "ResponsavelAbertura", nullable = false)
    private Integer responsavelAbertura;

    @Column(name = "DataAbertura", nullable = false)
    private LocalDateTime dataAbertura;

    @Column(name = "ResponsavelFechamento", nullable = false)
    private Integer responsavelFechamento;

    @Column(name = "DataFechamento", nullable = false)
    private LocalDateTime dataFechamento;

    @Column(name = "ValorAbertura", nullable = false)
    private Double valorAbertura;

    @Column(name = "ValorFechamento", nullable = false)
    private Double ValorFechamento;

    @Column(name = "Ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "DataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(name = "CriadoPor", nullable = false)
    private Integer criadoPor;

    @Column(name = "AlteradoPor", nullable = false)
    private Integer alteradoPor;
}