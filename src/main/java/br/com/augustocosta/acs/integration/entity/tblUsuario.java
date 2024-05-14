package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;
import java.time.*;

@Entity
@Table(name = "tbl_usuario")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioId")
    private Integer id;

    @Column(name = "CpfCnpj", length = 11, nullable = false)
    private String cpfCnpj;

    @Column(name = "Nome", nullable = false)
    private String nome;

    @Column(name = "Sobrenome", nullable = false)
    private String sobrenome;

    @Column(name = "Genero", nullable = false)
    private char genero;

    @Column(name = "DataNascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Senha", nullable = false)
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EnderecoId", nullable = false)
    private tblEndereco endereco;

    @Column(name = "DDICelular", nullable = false)
    private Integer ddiCelular;

    @Column(name = "DDDCelular", nullable = false)
    private Integer dddCelular;

    @Column(name = "Celular", nullable = false)
    private String celular;

    @Column(name = "DDITelefone")
    private Integer ddiTelefone;

    @Column(name = "DDDTelefone")
    private Integer dddTelefone;

    @Column(name = "Telefone")
    private String telefone;

    @Column(name = "Profissao")
    private String profissao;

    @Column(name = "Observacao")
    private String observacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CargoId")
    private tblCargo cargo;

    @ManyToOne(fetch = FetchType.EAGER)
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

    @OneToMany(mappedBy = "responsavelAbertura")
    private Set<tblCaixa> tblCaixa = new LinkedHashSet<>();

}