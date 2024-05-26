package br.com.augustocosta.acs.integration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tbl_produto")
public class tblProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProdutoId", nullable = false)
    private Integer id;

    @Column(name = "CodigoInterno")
    private Integer codigoInterno;

    @Column(name = "DescricaoProduto", nullable = false)
    private String descricaoProduto;

    @Column(name = "CodigoBarras", nullable = false, length = 13)
    private String codigoBarras;

    @Column(name = "EstoqueMinimo", nullable = false)
    private Integer estoqueMinimo;

    @Column(name = "Custo", nullable = false, precision = 18, scale = 2)
    private BigDecimal custo;

    @Column(name = "ValorVenda", nullable = false, precision = 18, scale = 2)
    private BigDecimal valorVenda;

    @Column(name = "Comissao", nullable = false)
    private Integer comissao;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MarcaId", nullable = false)
    private tblMarca marca;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CategoriaId", nullable = false)
    private tblCategoria categoria;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "LinhaId", nullable = false)
    private tblLinha linha;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CaracteristicaId", nullable = false)
    private tblCaracteristica caracteristica;

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