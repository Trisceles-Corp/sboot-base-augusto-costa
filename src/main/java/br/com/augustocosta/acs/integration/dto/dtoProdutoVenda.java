package br.com.augustocosta.acs.integration.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class dtoProdutoVenda {

    private Integer produtoId;
    private String nome;
    private String marca;
    private String linha;
    private Double preco;
    private Integer quantidade;

    public dtoProdutoVenda() {
    }
}
