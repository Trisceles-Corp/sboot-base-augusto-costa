package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_servicosagendamento")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblServicosAgendamento {

    @EmbeddedId
    private ServicosAgendamento id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("servicoId")
    @JoinColumn(name = "ServicoId", referencedColumnName = "ServicoId")
    private tblServico servico;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("agendamentoId")
    @JoinColumn(name = "AgendamentoId", referencedColumnName = "AgendamentoId")
    private tblAgendamento agendamento;

    @Column(name = "ValorUnitario", nullable = false)
    private Double valorUnitario;

    @Column(name = "Quantidade", nullable = false)
    private Integer quantidade;

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