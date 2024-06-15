package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblHorario;
import br.com.augustocosta.acs.integration.projections.*;
import br.com.augustocosta.acs.persistence.dao.SqlQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.Date;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<tblHorario, Integer> {
    List<tblHorario> findByHorario(LocalTime nome);
    List<tblHorario> findByAtivoTrueOrderByHorarioAsc();
    List<tblHorario> findByAtivoTrue();
    List<tblHorario> findByAtivoFalse();

    @Query(value = SqlQueries.SP_HORARIOSLIVRESCOLABORADOR, nativeQuery = true)
    List<prjHorario> findByHorarioColaborador(@Param("dataAgenda") Date dataAgenda, @Param("colaboradorId") Integer colaboradorId);
}
