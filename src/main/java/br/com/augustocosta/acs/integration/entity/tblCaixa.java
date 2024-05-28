package br.com.augustocosta.acs.integration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_caixa")
public class tblCaixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CaixaId", nullable = false)
    private Integer id;

    @Column(name = "Nome", nullable = false, length = 50)
    private String nome;

    @ColumnDefault("1")
    @Column(name = "NomeIndice")
    private Integer nomeIndice;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ResponsavelAbertura", nullable = false)
    private tblUsuario responsavelAbertura;

    @Column(name = "DataAbertura", nullable = false)
    private LocalDateTime dataAbertura;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ResponsavelFechamento")
    private tblUsuario responsavelFechamento;

    @Column(name = "DataFechamento")
    private LocalDateTime dataFechamento;

    @ColumnDefault("0")
    @Column(name = "ValorAbertura", nullable = false, precision = 18, scale = 2)
    private BigDecimal valorAbertura;

    @ColumnDefault("0")
    @Column(name = "ValorFechamento", nullable = false, precision = 18, scale = 2)
    private BigDecimal valorFechamento;

    @Column(name = "Ativo", nullable = false)
    private Boolean ativo = false;

    @Column(name = "DataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(name = "CriadoPor", nullable = false)
    private Integer criadoPor;

    @Column(name = "AlteradoPor", nullable = false)
    private Integer alteradoPor;

    @OneToMany(mappedBy = "caixa")
    private Set<tblCaixaMovimentacao> tblCaixamovimentacaos = new LinkedHashSet<>();
}