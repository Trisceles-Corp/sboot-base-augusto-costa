package br.com.augustocosta.acs.integration.dto;

import br.com.augustocosta.acs.integration.entity.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
public class dtoAgendamento {

    private tblAgendamento agendamento;
    private Integer localEstoqueId;
    private tblServicosAgendamento servicosAgendamento;
    private List<tblServico> servico;
    private tblVenda venda;
    private tblVendaProduto vendaProduto;
    private List<dtoProdutoVenda> produto;
    private Integer quantidade;

    public dtoAgendamento() {
    }
}
