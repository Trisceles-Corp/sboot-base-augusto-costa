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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProdutoId")
    private Integer id;

    @Column(name = "CodigoInterno")
    private Double codigoInterno;

    @Column(name = "DescricaoProduto", nullable = false)
    private String descricaoProduto;

    @Column(name = "CodigoBarras", nullable = false)
    private Double codigoBarras;

    @Column(name = "EstoqueMinimo", nullable = false)
    private Integer estoqueMinimo;

    @Column(name = "Custo", nullable = false)
    private Double custo;

    @Column(name = "ValorVenda", nullable = false)
    private Double valorVenda;

    @Column(name = "Comissao", nullable = false)
    private Double comissao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MarcaId", nullable = false)
    private tblMarca marca;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LinhaId", nullable = false)
    private tblLinha linha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CaracteristicaId", nullable = false)
    private tblCaracteristica caracteristica;

    @Column(name = "Ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "DataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CriadoPor", nullable = false)
    private tblUsuario criadoPor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AlteradoPor", nullable = false)
    private tblUsuario alteradoPor;
}