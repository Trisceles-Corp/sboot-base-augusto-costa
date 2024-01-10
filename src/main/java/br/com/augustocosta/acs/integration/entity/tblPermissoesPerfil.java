package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_permissoesperfil")
@Getter // Cria automaticamente os getters para todos os campos
@Setter // Cria automaticamente os setters para todos os campos
@ToString
@NoArgsConstructor // Cria um construtor sem argumentos
@AllArgsConstructor // Cria um construtor com todos os argumentos
public class tblPermissoesPerfil {

    @EmbeddedId
    private PermissoesPerfil id;

    @ManyToOne
    @MapsId("perfilId")
    @JoinColumn(name = "PerfilId", referencedColumnName = "PerfilId")
    private tblPerfil perfil;

    @ManyToOne
    @MapsId("permissaoId")
    @JoinColumn(name = "PermissaoId", referencedColumnName = "PermissaoId")
    private tblPermissoes permissao;

    @Column(name = "DataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(name = "CriadoPor", nullable = false)
    private Integer criadoPor;

    @Column(name = "AlteradoPor", nullable = false)
    private Integer alteradoPor;
}

