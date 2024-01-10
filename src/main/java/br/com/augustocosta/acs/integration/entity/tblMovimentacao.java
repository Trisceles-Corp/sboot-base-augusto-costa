package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_movimentacao")
@Getter // Cria automaticamente os getters para todos os campos
@Setter // Cria automaticamente os setters para todos os campos
@ToString
@NoArgsConstructor // Cria um construtor sem argumentos
@AllArgsConstructor // Cria um construtor com todos os argumentos
public class tblMovimentacao {

    @Id @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovimentacaoId")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoMovimentacaoId", nullable = false)
    private tblTipoMovimentacao tipoMovimentacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VendaId", nullable = false)
    private tblVenda venda;

    @Column(name = "Quantidade")
    private Integer quantidade;

    @Column(name = "Observacao")
    private String observacao;

    @Column(name = "Valor", nullable = false)
    private Double valor;

    @Column(name = "NrNotaFiscal", nullable = false)
    private Integer nrNotaFiscal;

    @Column(name = "SerieNotaFiscal", nullable = false)
    private String serieNotaFiscal;

    @Column(name = "DataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(name = "CriadoPor", nullable = false)
    private Integer criadoPor;

    @Column(name = "AlteradoPor", nullable = false)
    private Integer alteradoPor;
}
