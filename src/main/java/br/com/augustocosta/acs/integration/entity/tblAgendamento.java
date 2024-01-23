package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;

@Entity
@Table(name = "tbl_agendamento")
@Getter // Cria automaticamente os getters para todos os campos
@Setter // Cria automaticamente os setters para todos os campos
@ToString
@NoArgsConstructor // Cria um construtor sem argumentos
@AllArgsConstructor // Cria um construtor com todos os argumentos
public class tblAgendamento {

    @Id @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgendamentoId")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClienteId", nullable = false)
    private tblUsuario cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ColaboradorId", nullable = false)
    private tblUsuario colaborador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ServicoId", nullable = false)
    private tblServico servico;

    @Column(name = "DataAgendamento", nullable = false)
    private Date dataAgendamento;

    @Column(name = "HoraAgendamento", nullable = false)
    private LocalTime horaAgendamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BloqueioId")
    private tblBloqueio bloqueio;

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