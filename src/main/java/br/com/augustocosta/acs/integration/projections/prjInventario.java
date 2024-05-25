package br.com.augustocosta.acs.integration.projections;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

public interface prjInventario {
    Integer getLocalEstoqueId();
    String getDescricaoLocal();
    Integer getProdutoId();
    Integer getCodigoInterno();
    String getDescricaoProduto();
    Integer getQtdProduto();
    BigDecimal getValorProduto();
}
