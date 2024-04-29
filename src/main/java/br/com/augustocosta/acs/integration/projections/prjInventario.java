package br.com.augustocosta.acs.integration.projections;

import java.time.*;
import java.util.*;

public interface prjInventario {
    Integer getLocalEstoqueId();
    String getDescricaoLocal();
    Integer getProdutoId();
    Integer getCodigoInterno();
    String getDescricaoProduto();
    Integer getQtdProduto();
    Double getValorProduto();
}
