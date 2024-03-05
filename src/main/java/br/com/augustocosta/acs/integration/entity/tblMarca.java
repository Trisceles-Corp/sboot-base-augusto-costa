package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_marca")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblMarca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MarcaId")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoriaId", nullable = false)
    private tblAgendamento categoria;

    @Column(name = "DescricaoMarca", nullable = false)
    private String descricaoMarca;

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