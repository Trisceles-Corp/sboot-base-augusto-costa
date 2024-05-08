package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_caixamovimentacao")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblCaixaMovimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CaixaMovimentacaoId")
    private Integer id;

    @Column(name = "CaixaId", nullable = false)
    private Integer caixaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ComandaId")
    private tblComanda comanda;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoMovimentacaoId", nullable = false)
    private tblTipoMovimentacao tipoMovimentacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FormaPagamentoId", nullable = false)
    private tblFormasPagamento formaPagamento;

    @Column(name = "ValorMovimentacao", nullable = false)
    private Double valorMovimentacao;

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