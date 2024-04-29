package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_bandeiras")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblBandeiras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BandeiraId")
    private Integer id;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "MaxParcelas")
    private Integer maxParcelas = 0;

    @Column(name = "JurosParcelas")
    private Double jurosParcelas = 0.0;

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