package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_movimentacaoProduto")
@Getter // Cria automaticamente os getters para todos os campos
@Setter // Cria automaticamente os setters para todos os campos
@ToString
@NoArgsConstructor // Cria um construtor sem argumentos
@AllArgsConstructor // Cria um construtor com todos os argumentos
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

