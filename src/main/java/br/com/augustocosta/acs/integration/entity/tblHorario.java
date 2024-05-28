package br.com.augustocosta.acs.integration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_horario")
public class tblHorario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HorarioId", nullable = false)
    private Integer id;

    @Column(name = "Horario", nullable = false)
    private LocalTime horario;

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

    @Nationalized
    @ColumnDefault("1")
    @Column(name = "AlteradoPor", nullable = false, length = 10)
    private Integer alteradoPor;

}