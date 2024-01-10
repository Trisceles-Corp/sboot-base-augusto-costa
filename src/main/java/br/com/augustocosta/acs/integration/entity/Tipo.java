package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_tipo")
@Getter(lazy = true) // Cria automaticamente os getters para todos os campos
@Setter // Cria automaticamente os setters para todos os campos
@ToString
@NoArgsConstructor // Cria um construtor sem argumentos
@AllArgsConstructor // Cria um construtor com todos os argumentos
public class Tipo {

    @Id @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TipoId")
    private Integer id;

    @Column(name = "Descricao", nullable = false)
    private String descricao;

    @Column(name = "Ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "DataCriacao", nullable = false)
    private Date dataCriacao = new Date();

    @Column(name = "DataAlteracao", nullable = false)
    private Date dataAlteracao = new Date();

    @Column(name = "CriadoPor", nullable = false)
    private Integer criadoPor;

    @Column(name = "AlteradoPor", nullable = false)
    private Integer alteradoPor;
}