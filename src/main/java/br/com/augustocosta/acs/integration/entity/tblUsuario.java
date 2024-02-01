package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;
import java.time.*;

@Entity
@Table(name = "tbl_usuario")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblUsuario {

    @Id @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioId")
    private Integer id;

    @Column(name = "CPF", nullable = false)
    private Double cpf;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Sobrenome", nullable = false)
    private String sobrenome;

    @Column(name = "Genero", nullable = false)
    private char genero;

    @Column(name = "DataNascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Senha", nullable = false)
    private String senha;

    @Column(name = "Endereco")
    private String endereco;

    @Column(name = "DDICelular", nullable = false)
    private Integer ddiCelular;

    @Column(name = "DDDCelular", nullable = false)
    private Integer dddCelular;

    @Column(name = "Celular", nullable = false)
    private Double celular;

    @Column(name = "DDITelefone")
    private Integer ddiTelefone;

    @Column(name = "DDDTelefone")
    private Integer dddTelefone;

    @Column(name = "Telefone")
    private Double telefone;

    @Column(name = "Profissao")
    private String profissao;

    @Column(name = "Observacao")
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CargoId")
    private tblCargo cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PerfilId", nullable = false)
    private tblPerfil perfil;

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