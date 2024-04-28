package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_saidaproduto")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblSaidaProduto {

    @EmbeddedId
    private SaidaProduto id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SaidaId", nullable = false)
    private tblSaida saida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProdutoId", nullable = false)
    private tblProduto produto;

    @Column(name = "ValorUnitario", nullable = false)
    private Double valorUnitario;

    @Column(name = "Quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "ValorTotal")
    private Double valorTotal;

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