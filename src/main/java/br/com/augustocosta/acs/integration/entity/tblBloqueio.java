package br.com.augustocosta.acs.integration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_bloqueio")
public class tblBloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BloqueioId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PeriodoId", nullable = false)
    private tblPeriodo periodo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DiasSemanaId")
    private tblDiasSemana diasSemana;

    @Column(name = "DataBloqueio", nullable = false)
    private LocalDate dataBloqueio;

    @Column(name = "HoraInicial", nullable = false)
    private LocalTime horaInicial;

    @Column(name = "HoraFinal", nullable = false)
    private LocalTime horaFinal;

    @Column(name = "MotivoBloqueio", nullable = false, length = 500)
    private String motivoBloqueio;

    @Column(name = "Ativo", columnDefinition = "tinyint not null")
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