package br.com.augustocosta.acs.integration.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class dtoLogin {
    private String email;
    private String senha;

    public dtoLogin(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
}
