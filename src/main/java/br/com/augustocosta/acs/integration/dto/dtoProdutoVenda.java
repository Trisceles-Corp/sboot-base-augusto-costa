package br.com.augustocosta.acs.integration.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class dtoProdutoVenda {

    private Integer produtoId;
    private Integer quantidade;

    public dtoProdutoVenda() {
    }
}
