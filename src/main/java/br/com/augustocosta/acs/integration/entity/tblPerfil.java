package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_perfil")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PerfilId")
    private Integer id;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoPerfilId", nullable = false)
    private tblTipoPerfil tipoPerfil;

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