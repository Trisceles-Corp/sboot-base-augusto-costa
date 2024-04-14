package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_compra")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CompraId")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LocalEstoqueId", nullable = false)
    private tblLocalEstoque localEstoque;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SituacaoCompraId", nullable = false)
    private tblSituacaoCompra situacaoCompra;

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