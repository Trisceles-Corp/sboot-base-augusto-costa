package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblDiasSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiasSemanaRepository extends JpaRepository<tblDiasSemana, Integer> {
    List<tblDiasSemana> findByNome(String diasSemana);
    List<tblDiasSemana> findByAtivoTrue();
    List<tblDiasSemana> findByAtivoFalse();
}

