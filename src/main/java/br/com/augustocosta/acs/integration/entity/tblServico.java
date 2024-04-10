package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_servico")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServicoId")
    private Integer id;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Tempo", nullable = false)
    private LocalTime tempo;

    @Column(name = "Valor", nullable = false)
    private Double valor;

    @Column(name = "Observacao")
    private String observacao;

    @Column(name = "Desconto")
    private Double desconto;

    @Column(name = "Comissao")
    private Double comissao;

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

    // Getters personalizados para tratar null como zero
    public double getValor() {
        return valor != null ? valor: 0.0;
    }

    public double getDesconto() {
        return desconto != null ? desconto: 0.0;
    }

    public double getComissao() {
        return comissao != null ? comissao: 0.0;
    }
}