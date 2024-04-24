package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_comandaPagamento")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblComandaPagamento {

    @EmbeddedId
    private ComandaPagamento id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ComandaId", nullable = false)
    private tblComanda comanda;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FormaPagamentoId", nullable = false)
    private tblFormasPagamento formaPagamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BandeiraId", nullable = false)
    private tblBandeiras bandeira;

    @Column(name = "Valor", nullable = false)
    private Double valor = 0.0;

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