package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;

@Entity
@Table(name = "tbl_bloqueio")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblBloqueio {

    @Id @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BloqueioId")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PeriodoId", nullable = false)
    private tblPeriodo periodo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DiasSemanaId", nullable = false)
    private tblDiasSemana diasSemana;

    @Column(name = "DataBloqueio", nullable = false)
    private Date dataBloqueio;

    @Column(name = "HoraInicial", nullable = false)
    private LocalTime horaInicial;

    @Column(name = "HoraFinal", nullable = false)
    private LocalTime horaFinal;

    @Column(name = "MotivoBloqueio", nullable = false)
    private String motivoBloqueio;

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