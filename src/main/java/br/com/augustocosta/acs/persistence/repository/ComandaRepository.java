package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblComanda;
import br.com.augustocosta.acs.integration.entity.tblAgendamento ;
import br.com.augustocosta.acs.integration.projections.prjComanda;
import br.com.augustocosta.acs.persistence.dao.SqlQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ComandaRepository extends JpaRepository<tblComanda , Integer> {
    List<tblComanda> findByAgendamento(tblAgendamento agendamento);
    List<tblComanda> findByAtivoTrue();
    List<tblComanda> findByAtivoFalse();
    List<tblComanda> findBySituacaoTrueAndAtivoTrue();

    @Query(value = SqlQueries.QUERY_COMANDAS, nativeQuery = true)
    List<prjComanda> findComandasByAtivo();

    @Query(value = SqlQueries.QUERY_COMISSOES, nativeQuery = true)
    List<prjComanda> findComissoesByAtivo(@Param("colaboradorId") Integer colaboradorId, @Param("dataInc") LocalDate dataInc, @Param("dataFim") LocalDate dataFim);

}
