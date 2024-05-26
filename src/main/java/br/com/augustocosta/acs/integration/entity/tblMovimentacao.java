package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "tbl_movimentacao")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblMovimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovimentacaoId")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoMovimentacaoId", nullable = false)
    private tblTipoMovimentacao tipoMovimentacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CompraId")
    private tblCompra compra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "VendaId")
    private tblVenda venda;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SaidaId")
    private tblSaida saida;

    @Column(name = "Observacao")
    private String observacao;

    @Column(name = "ValorTotal", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "NrNotaFiscal", nullable = false)
    private Integer nrNotaFiscal;

    @Column(name = "SerieNotaFiscal", nullable = false)
    private String serieNotaFiscal;

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
