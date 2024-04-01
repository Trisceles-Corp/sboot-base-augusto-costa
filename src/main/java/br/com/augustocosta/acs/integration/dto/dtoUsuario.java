package br.com.augustocosta.acs.integration.dto;

import lombok.*;
import java.time.*;

@Getter
@Setter
@ToString
public class dtoUsuario {

    private Integer usuarioId;
    private String cpfCnpj;
    private String nome;
    private String sobrenome;
    private String nomeCompleto;
    private Character genero;
    private String generoDescricao;
    private LocalDate dataNascimento;
    private String email;
    private String senha;
    private Integer enderecoId;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private Integer ddiCelular;
    private Integer dddCelular;
    private String celular;
    private String celularCompleto;
    private Integer ddiTelefone;
    private Integer dddTelefone;
    private String telefone;
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

    public dtoUsuario() {
    }
}
