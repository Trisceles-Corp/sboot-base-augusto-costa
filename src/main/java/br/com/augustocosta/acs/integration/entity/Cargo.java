package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_cargo")
@Getter(lazy = true) // Cria automaticamente os getters para todos os campos
@Setter // Cria automaticamente os setters para todos os campos
@ToString
@NoArgsConstructor // Cria um construtor sem argumentos
@AllArgsConstructor // Cria um construtor com todos os argumentos
public class Cargo {

    @Id @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CargoId")
    private Integer id;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "DataCriacao", nullable = false)
    private Date dataCriacao;

    @Column(name = "DataAlteracao", nullable = false)
    private Date dataAlteracao;

    @Column(name = "CriadoPor", nullable = false)
    private Integer criadoPor;

    @Column(name = "AlteradoPor", nullable = false)
    private Integer alteradoPor;
}