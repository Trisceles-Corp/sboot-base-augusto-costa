package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_compraproduto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblCompraProduto {

    @EmbeddedId
    private CompraProduto id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("CompraId")
    @JoinColumn(name = "CompraId", nullable = false, referencedColumnName = "CompraId")
    private tblCompra compra;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("ProdutoId")
    @JoinColumn(name = "ProdutoId", nullable = false, referencedColumnName = "ProdutoId")
    private tblProduto produto;

    @Column(name = "ValorUnitario", nullable = false)
    private BigDecimal valorUnitario;

    @Column(name = "Quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "ValorTotal")
    private BigDecimal valorTotal;

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