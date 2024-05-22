package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblBloqueio;
import br.com.augustocosta.acs.integration.entity.tblPeriodo;
import br.com.augustocosta.acs.integration.entity.tblDiasSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BloqueioRepository extends JpaRepository<tblBloqueio, Integer> {
    List<tblBloqueio> findByPeriodo(tblPeriodo periodo);
    List<tblBloqueio> findByDiasSemana(tblDiasSemana diassemana);
    List<tblBloqueio> findByDataBloqueio(Date dataBloqueio);
    List<tblBloqueio> findByCriadoPorAndAtivoTrueOrderByDataBloqueioAsc(Integer criadoPor);
    List<tblBloqueio> findByAtivoTrue();
    List<tblBloqueio> findByAtivoFalse();
}

