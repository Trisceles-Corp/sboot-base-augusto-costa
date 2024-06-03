package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import br.com.augustocosta.acs.integration.entity.tblSaida ;
import br.com.augustocosta.acs.persistence.dao.SqlQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaidaRepository extends JpaRepository<tblSaida , Integer> {
    List<tblSaida> findByLocalEstoque(tblLocalEstoque localEstqoue);
    List<tblSaida> findBySolicitante(tblUsuario solicitante);
    List<tblSaida > findByAtivoTrue();
    List<tblSaida > findByAtivoTrueAndEstoqueFalse();
    List<tblSaida > findByAtivoFalse();

    @Modifying
    @Query(value = SqlQueries.SP_EFETIVASAIDAPRODUTOS, nativeQuery = true)
    void findByCommitSaidaProdutos(@Param("saidaId") Integer saidaId);

}
