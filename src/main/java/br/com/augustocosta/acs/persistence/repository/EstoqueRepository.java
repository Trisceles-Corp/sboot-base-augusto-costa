package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.integration.projections.prjInventario;
import br.com.augustocosta.acs.integration.projections.prjUsuario;
import br.com.augustocosta.acs.persistence.dao.SqlQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<tblEstoque, Integer> {
    List<tblEstoque> findByProduto(tblProduto produto);
    List<tblEstoque> findByLocalEstoque(tblLocalEstoque localEstoque);
    List<tblEstoque> findByMovimentacao(tblMovimentacao movimentacao);
    List<tblEstoque> findByAtivoTrue();
    List<tblEstoque> findByAtivoFalse();
    @Query(value = SqlQueries.QUERY_INVENTARIO, nativeQuery = true)
    List<prjInventario> findByInventario();
}
