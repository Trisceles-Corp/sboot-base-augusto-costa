package br.com.augustocosta.acs.integration.projections;

import java.time.*;
import java.util.Date;

public interface prjUsuario {
    Integer getUsuarioId();
    String getCpf();
    String getNome();
    String getSobrenome();
    String getNomeCompleto();
    char getGenero();
    String getGeneroDescricao();
    Date getDataNascimento();
    String getEmail();
    String getSenha();
    Integer getEnderecoId();
    String getCep();
    String getLogradouro();
    Integer getNumero();
    String getComplemento();
    String getBairro();
    String getCidade();
    String getUf();
    Integer getDdiCelular();
    Integer getDddCelular();
    Double getCelular();
    Integer getDdiTelefone();
    Integer getDddTelefone();
    Double getTelefone();
    String getProfissao();
    String getObservacao();
    Integer getCargoId();
    String getCargoNome();
    Integer getPerfilId();
    String getPerfilNome();
    Boolean getAtivo();
    LocalDateTime getDataAlteracao();
    Integer getAlteradoPor();

}