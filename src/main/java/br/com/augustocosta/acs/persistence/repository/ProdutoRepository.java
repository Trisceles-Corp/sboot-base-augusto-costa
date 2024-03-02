package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblProduto;
import br.com.augustocosta.acs.integration.entity.tblMarca;
import br.com.augustocosta.acs.integration.entity.tblLinha;
import br.com.augustocosta.acs.integration.entity.tblCaracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<tblProduto, Integer> {
    List<tblProduto> findByCodigoInterno(Integer codigoInterno);
    List<tblProduto> findByDescricaoProduto(String descricaoProduto);
    List<tblProduto> findByAtivoTrueOrderByDescricaoProdutoAsc();
    List<tblProduto> findByMarca(tblMarca marca);
    List<tblProduto> findByLinha(tblLinha linha);
    List<tblProduto> findByCaracteristica(tblCaracteristica caracteristica);
    List<tblProduto> findByAtivoTrue();
    List<tblProduto> findByAtivoFalse();
}
