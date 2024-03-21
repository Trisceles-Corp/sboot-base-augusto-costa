package br.com.augustocosta.acs.integration.dto;

import lombok.*;
import java.time.*;
import java.util.Date;

@Getter
@Setter
@ToString
public class dtoUsuario {

    private Integer usuarioId;
    private Double cpf;
    private String nome;
    private String sobrenome;
    private String nomeCompleto;
    private char genero;
    private String generoDescricao;
    private Date dataNascimento;
    private String email;
    private String senha;
    private Integer enderecoId;
    private String cep;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private Integer ddiCelular;
    private Integer dddCelular;
    private Double celular;
    private String celularCompleto;
    private Integer ddiTelefone;
    private Integer dddTelefone;
    private Double telefone;
    private String telefoneCompleto;
    private String profissao;
    private String observacao;
    private Integer cargoId;
    private String cargoNome;
    private Integer perfilId;
    private String perfilNome;
    private Boolean ativo;
    private LocalDateTime dataAlteracao;
    private Integer alteradoPor;

    public dtoUsuario(
            Integer usuarioId,
            double cpf,
            String nome,
            String sobrenome,
            String nomeCompleto,
            char genero,
            String generoDescricao,
            Date dataNascimento,
            String email,
            String senha,
            Integer enderecoId,
            String cep,
            String logradouro,
            Integer numero,
            String complemento,
            String bairro,
            String cidade,
            String uf,
            Integer ddiCelular,
            Integer dddCelular,
            Double celular,
            String celularCompleto,
            Integer ddiTelefone,
            Integer dddTelefone,
            Double telefone,
            String telefoneCompleto,
            String profissao,
            String observacao,
            Integer cargoId,
            String cargoNome,
            Integer perfilId,
            String perfilNome,
            Boolean ativo,
            LocalDateTime dataAlteracao,
            Integer alteradoPor
    ) {
        this.usuarioId = usuarioId;
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nomeCompleto = nomeCompleto;
        this.genero = genero;
        this.generoDescricao = generoDescricao;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.enderecoId = enderecoId;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.ddiCelular = ddiCelular;
        this.dddCelular = dddCelular;
        this.celular = celular;
        this.celularCompleto = celularCompleto;
        this.ddiTelefone = ddiTelefone;
        this.dddTelefone = dddTelefone;
        this.telefone = telefone;
        this.telefoneCompleto = telefoneCompleto;
        this.profissao = profissao;
        this.observacao = observacao;
        this.cargoId = cargoId;
        this.cargoNome = cargoNome;
        this.perfilId = perfilId;
        this.perfilNome = perfilNome;
        this.ativo = ativo;
        this.dataAlteracao = dataAlteracao;
        this.alteradoPor = alteradoPor;
    }
}
