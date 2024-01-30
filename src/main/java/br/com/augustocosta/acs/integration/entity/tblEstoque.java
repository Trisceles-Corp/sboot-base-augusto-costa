package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_estoque")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblEstoque {

    @Id @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EstoqueId")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProdutoId", nullable = false)
    private tblProduto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LocalEstoqueId", nullable = false)
    private tblLocalEstoque localEstoque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MovimentacaoId", nullable = false)
    private tblMovimentacao movimentacao;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "DataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(name = "CriadoPor", nullable = false)
    private Integer criadoPor;

    @Column(name = "AlteradoPor", nullable = false)
    private Integer alteradoPor;
}