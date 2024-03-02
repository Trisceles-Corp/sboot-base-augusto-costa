package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.*;

@Repository
public interface PeriodoRepository extends JpaRepository<tblPeriodo, Integer> {
    List<tblPeriodo> findByNome(String nome);
    List<tblPeriodo> findByAtivoTrueOrderByNomeAsc();
    List<tblPeriodo> findByAtivoTrue();
    List<tblPeriodo> findByAtivoFalse();
}
