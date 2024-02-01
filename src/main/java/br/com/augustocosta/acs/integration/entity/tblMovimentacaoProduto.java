package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;
import java.util.Objects;

@Entity
@Table(name = "tbl_movimentacaoProduto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblMovimentacaoProduto {

    @EmbeddedId
    private MovimentacaoProduto id;

    @ManyToOne
    @MapsId("movimentacaoId")
    @JoinColumn(name = "MovimentacaoId", referencedColumnName = "MovimentacaoId")
    private tblMovimentacao movimentacao;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "ProdutoId", referencedColumnName = "ProdutoId")
    private tblProduto produto;

    @Column(name = "ValorUnitario", nullable = false)
    private Double valorUnitario;

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
