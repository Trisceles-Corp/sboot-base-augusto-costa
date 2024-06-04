package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<tblProduto, Integer> {
    List<tblProduto> findByCodigoInterno(Integer codigoInterno);
    List<tblProduto> findByDescricaoProduto(String descricaoProduto);
    List<tblProduto> findByAtivoTrueOrderByDescricaoProdutoAsc();
    List<tblProduto> findByMarca(tblMarca marca);
    List<tblProduto> findByCategoria(tblCategoria categoria);
    List<tblProduto> findByLinha(tblLinha linha);
    List<tblProduto> findByCaracteristica(tblCaracteristica caracteristica);
    List<tblProduto> findByCriadoPor(tblUsuario usuario);
    List<tblProduto> findByAlteradoPor(tblUsuario usuario);
    List<tblProduto> findByAtivoTrue();
    List<tblProduto> findByAtivoFalse();
}
