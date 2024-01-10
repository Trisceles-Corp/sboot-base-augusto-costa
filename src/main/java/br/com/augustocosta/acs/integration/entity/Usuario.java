package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_usuario")
@Getter(lazy = true) // Cria automaticamente os getters para todos os campos
@Setter // Cria automaticamente os setters para todos os campos
@ToString
@NoArgsConstructor // Cria um construtor sem argumentos
@AllArgsConstructor // Cria um construtor com todos os argumentos
public class Usuario {

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
    private Cargo cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PerfilId", nullable = false)
    private Perfil perfil;

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