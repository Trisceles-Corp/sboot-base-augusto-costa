package br.com.augustocosta.acs.integration.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.*;

@Getter
@Setter
@ToString
public class dtoInventario {

    private Integer localEstoqueId;
    private String descricaoLocal;
    private Integer produtoId;
    private Integer codigoInterno;
    private String descricaoProduto;
    private Integer qtdProduto;
    private BigDecimal valorProduto;

    public dtoInventario() {
    }
}
