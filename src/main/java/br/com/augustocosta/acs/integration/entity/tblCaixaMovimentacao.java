package br.com.augustocosta.acs.integration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tbl_caixamovimentacao")
public class tblCaixaMovimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CaixaMovimentacaoId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CaixaId", nullable = false)
    private tblCaixa caixa;

    @Column(name = "ComandaId")
    private Integer comandaId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "TipoMovimentacaoId", nullable = false)
    private tblTipoMovimentacao tipoMovimentacao;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "FormaPagamentoId", nullable = false)
    private tblFormasPagamento formaPagamento;

    @ColumnDefault("0")
    @Column(name = "ValorMovimentacao", nullable = false, precision = 18, scale = 2)
    private BigDecimal valorMovimentacao;

    @Column(name = "Observacao")
    private String observacao;

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
}