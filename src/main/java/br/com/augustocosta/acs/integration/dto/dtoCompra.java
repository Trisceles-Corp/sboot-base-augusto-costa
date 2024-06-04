package br.com.augustocosta.acs.integration.dto;

import br.com.augustocosta.acs.integration.entity.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class dtoCompra {

    private Integer id;
    private Integer localEstoqueId;
    private Integer situacaoCompraId;
    private BigDecimal valorTotal;
    private Boolean estoque;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private Integer criadoPor;
    private Integer alteradoPor;
    private List<tblCompraProduto> compraProdutos;
    private Integer produtoId;
    private Integer quantidade;

    public dtoCompra() {
    }
}