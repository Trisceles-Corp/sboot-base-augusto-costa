package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "tbl_estoque")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EstoqueId")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProdutoId", nullable = false)
    private tblProduto produto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LocalEstoqueId", nullable = false)
    private tblLocalEstoque localEstoque;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MovimentacaoId", nullable = false)
    private tblMovimentacao movimentacao;

    @Column(name = "Quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "ValorMovimentacao", nullable = false)
    private BigDecimal valorMovimentacao;

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