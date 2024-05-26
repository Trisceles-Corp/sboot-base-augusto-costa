package br.com.augustocosta.acs.integration.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class dtoProdutoVenda {

    private Integer produtoId;
    private String nome;
    private String marca;
    private String linha;
    private BigDecimal preco;
    private Integer quantidade;

    public dtoProdutoVenda() {
    }
}
