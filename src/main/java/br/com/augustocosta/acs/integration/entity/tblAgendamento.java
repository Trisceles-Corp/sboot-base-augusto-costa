package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;

@Entity
@Table(name = "tbl_agendamento")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblAgendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgendamentoId")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ClienteId", nullable = false)
    private tblUsuario cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ColaboradorId", nullable = false)
    private tblUsuario colaborador;

    @Column(name = "DataAgendamento", nullable = false)
    private Date dataAgendamento;

    @Column(name = "HoraAgendamento", nullable = false)
    private LocalTime horaAgendamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SituacaoId")
    private tblSituacaoAgendamento situacao;

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