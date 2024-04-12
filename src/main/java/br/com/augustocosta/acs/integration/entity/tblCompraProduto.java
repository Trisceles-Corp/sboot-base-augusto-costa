package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
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
    @JoinColumn(name = "CompraId", nullable = false)
    private tblCompra compra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProdutoId", nullable = false)
    private tblProduto produto;

    @Column(name = "ValorUnitario", nullable = false)
    private Double valorUnitario;

    @Column(name = "Quantidade", nullable = false)
    private Integer quantidade;

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