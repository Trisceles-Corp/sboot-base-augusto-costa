package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_comanda")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblComanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ComandaId")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AgendamentoId", nullable = false)
    private tblAgendamento agendamento;

    @Column(name = "ValorServicos")
    private Double valorServicos = 0.0;

    @Column(name = "ValorDescontos")
    private Double valorDescontos = 0.0;

    @Column(name = "ValorComissao")
    private Double valorComissao = 0.0;

    @Column(name = "ValorProdutos")
    private Double valorProdutos = 0.0;

    @Column(name = "ValorEncargos")
    private Double valorEncargos = 0.0;

    @Column(name = "Situacao")
    private Boolean situacao = true;

    @Column(name = "Ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "DataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(name = "CriadoPor", nullable = false)
    private Integer criadoPor;

    @Column(name = "AlteradoPor", nullable = false)
    private Integer alteradoPor;
}