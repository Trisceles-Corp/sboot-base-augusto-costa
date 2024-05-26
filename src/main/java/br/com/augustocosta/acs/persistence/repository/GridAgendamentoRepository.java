package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblGridAgendamento;
import br.com.augustocosta.acs.integration.projections.prjGridAgendamento;
import br.com.augustocosta.acs.persistence.dao.SqlQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GridAgendamentoRepository extends JpaRepository<tblGridAgendamento, Integer> {
    List<tblGridAgendamento> findByAtivoTrue();
    List<tblGridAgendamento> findByAtivoFalse();

    @Query("SELECT ga FROM tblGridAgendamento ga WHERE ga.agendameto.dataAgendamento = :dataAgendamento")
    List<tblGridAgendamento> findByDataAgendamento(@Param("dataAgendamento") LocalDate dataAgendamento);

    @Query(value = SqlQueries.SP_OBTERAGENDA, nativeQuery = true)
    List<prjGridAgendamento> findByGridAgendamento(@Param("dataAgenda") LocalDate dataAgenda);
}