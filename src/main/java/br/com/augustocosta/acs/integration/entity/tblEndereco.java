package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_endereco")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EnderecoId")
    private Integer id;

    @Column(name = "CEP", nullable = false)
    private String cep;

    @Column(name = "Logradouro", nullable = false)
    private String logradouro;

    @Column(name = "Numero")
    private String numero;

    @Column(name = "Complemento")
    private String complemento;

    @Column(name = "Bairro", nullable = false)
    private String bairro;

    @Column(name = "Cidade", nullable = false)
    private String cidade;

    @Column(name = "UF", nullable = false)
    private String uf;

    @Column(name = "Observacao")
    private String observacao;

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