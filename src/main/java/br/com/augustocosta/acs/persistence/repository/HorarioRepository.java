package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<tblHorario, Integer> {
    List<tblHorario> findByHorario(LocalTime nome);
    List<tblHorario> findByAtivoTrueOrderByHorarioAsc();
    List<tblHorario> findByAtivoTrue();
    List<tblHorario> findByAtivoFalse();
}
