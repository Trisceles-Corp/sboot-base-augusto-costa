package br.com.augustocosta.acs.integration.projections;

import java.time.*;
import java.util.*;

public interface prjUsuario {
    Integer getUsuarioId();
    String getCpfCnpj();
    String getNome();
    String getSobrenome();
    String getNomeCompleto();
    Character getGenero();
    String getGeneroDescricao();
    LocalDate getDataNascimento();
    String getEmail();
    String getSenha();
    Integer getEnderecoId();
    String getCep();
    String getLogradouro();
    String getNumero();
    String getComplemento();
    String getBairro();
    String getCidade();
    String getUf();
    Integer getDdiCelular();
    Integer getDddCelular();
    String getCelular();
    Integer getDdiTelefone();
    Integer getDddTelefone();
    String getTelefone();
    String getProfissao();
    String getObservacao();
    Integer getCargoId();
    String getCargoNome();
    Integer getPerfilId();
    String getPerfilNome();
    Boolean getAtivo();
    LocalDateTime getDataAlteracao();
    Integer getAlteradoPor();

    default String getCelularCompleto() {
        return "+" + getDdiCelular() + " (" + getDddCelular() + ") " + getCelular();
    }

    default String getTelefoneCompleto() {
        return "+" + getDdiTelefone() + " (" + getDddTelefone() + ") " + getTelefone();
    }
}