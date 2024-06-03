package br.com.augustocosta.acs.integration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tbl_gridagendamento")
public class tblGridAgendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GridAgendamentoId", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ComandaId", nullable = false)
    private tblComanda comanda;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "AgendamentoId", nullable = false)
    private tblAgendamento agendamento;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ServicoId", nullable = false)
    private tblServico servico;

    @Column(name = "Situacao")
    private String situacao;

    @ColumnDefault("1")
    @Column(name = "Ativo", nullable = false)
    private Boolean ativo = false;

    @ColumnDefault("getdate()")
    @Column(name = "DataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ColumnDefault("getdate()")
    @Column(name = "DataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @ColumnDefault("1")
    @Column(name = "CriadoPor", nullable = false)
    private Integer criadoPor;

    @ColumnDefault("1")
    @Column(name = "AlteradoPor", nullable = false)
    private Integer alteradoPor;

}