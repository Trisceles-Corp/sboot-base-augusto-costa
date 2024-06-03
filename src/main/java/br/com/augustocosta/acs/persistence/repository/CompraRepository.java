package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCompra;
import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import br.com.augustocosta.acs.integration.entity.tblSituacaoCompra;
import br.com.augustocosta.acs.integration.projections.prjComanda;
import br.com.augustocosta.acs.persistence.dao.SqlQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<tblCompra , Integer> {
    List<tblCompra> findByLocalEstoque(tblLocalEstoque localEstqoue);
    List<tblCompra> findBySituacaoCompra(tblSituacaoCompra situacaoCompra);
    List<tblCompra > findByAtivoTrue();
    List<tblCompra > findByAtivoTrueAndEstoqueFalse();
    List<tblCompra > findByAtivoFalse();

    @Modifying
    @Query(value = SqlQueries.SP_EFETIVACOMPRAPRODUTOS, nativeQuery = true)
    void findByCommitCompraProdutos(@Param("compraId") Integer compraId);
}
