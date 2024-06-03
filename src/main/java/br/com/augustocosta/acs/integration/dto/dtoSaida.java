package br.com.augustocosta.acs.integration.dto;

import br.com.augustocosta.acs.integration.entity.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class dtoSaida {

    private Integer id;
    private Integer solicitanteId;
    private Integer localEstoqueId;
    private BigDecimal valorTotal;
    private Boolean estoque;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private Integer criadoPor;
    private Integer alteradoPor;
    private List<tblSaidaProduto> saidaProdutos;
    private Integer produtoId;
    private Integer quantidade;

    public dtoSaida() {
    }
}