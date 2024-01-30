package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_produto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblProduto {

    @Id @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProdutoId")
    private Integer id;

    @Column(name = "CodigoInterno")
    private Integer codigoInterno;

    @Column(name = "DescricaoProduto", nullable = false)
    private String descricaoProduto;

    @Column(name = "CodigoBarras", nullable = false)
    private Double codigoBarras;

    @Column(name = "Categoria", nullable = false)
    private String categoria;

    @Column(name = "EstoqueMinimo", nullable = false)
    private Integer estoqueMinimo;

    @Column(name = "Custo", nullable = false)
    private Double custo;

    @Column(name = "ValorVenda", nullable = false)
    private Double valorVenda;

    @Column(name = "Comissao", nullable = false)
    private Double comissao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MarcaId")
    private tblMarca marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LinhaId")
    private tblLinha linha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CaracteristicaId")
    private tblCaracteristica caracteristica;

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