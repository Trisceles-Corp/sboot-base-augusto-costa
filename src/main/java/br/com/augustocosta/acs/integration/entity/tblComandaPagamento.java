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
    @MapsId("comandaId")
    @JoinColumn(name = "ComandaId", referencedColumnName = "ComandaId")
    private tblComanda comanda;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("formaPagamentoId")
    @JoinColumn(name = "FormaPagamentoId", referencedColumnName = "FormaPagamentoId")
    private tblFormasPagamento formaPagamento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BandeiraId", nullable = false)
    private tblBandeiras bandeira;

    @Column(name = "Parcelas")
    private Integer parcelas = 1;

    @Column(name = "Valor", nullable = false)
    private Double valorPagamento = 0.0;

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