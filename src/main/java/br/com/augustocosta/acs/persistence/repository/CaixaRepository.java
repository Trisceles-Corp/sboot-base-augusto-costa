package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCaixa;
import br.com.augustocosta.acs.integration.projections.prjCaixa;
import br.com.augustocosta.acs.persistence.dao.SqlQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaixaRepository extends JpaRepository<tblCaixa , Integer> {
    @Query(value = SqlQueries.QUERY_CAIXAS, nativeQuery = true)
    List<prjCaixa> findCaixasByAtivo();

    @Query(value = SqlQueries.QUERY_MAXINDICECAIXA, nativeQuery = true)
    Integer findMaxCaixaIndiceForToday();

    List<tblCaixa> findByNome(String nome);
    List<tblCaixa> findByAtivoTrueOrderByNomeAsc();
    List<tblCaixa> findByAtivoTrue();
    List<tblCaixa> findByAtivoFalse();
}
