package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_compra")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CompraId")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LocalEstoqueId", nullable = false)
    private tblLocalEstoque localEstoque;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SituacaoCompraId", nullable = false)
    private tblSituacaoCompra situacaoCompra;

    @ColumnDefault("0")
    @Column(name = "ValorTotal")
    private BigDecimal valorTotal;

    @Column(name = "Estoque")
    private Boolean estoque = false;

    @Column(name = "Ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "DataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(name = "CriadoPor", nullable = false)
    private Integer criadoPor;

    @Column(name = "AlteradoPor", nullable = false)
    private Integer alteradoPor;
}