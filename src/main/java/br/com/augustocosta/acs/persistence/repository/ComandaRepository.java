package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblComanda;
import br.com.augustocosta.acs.integration.entity.tblAgendamento ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComandaRepository extends JpaRepository<tblComanda , Integer> {
    List<tblComanda> findByAgendamento(tblAgendamento agendamento);
    List<tblComanda> findByAtivoTrue();
    List<tblComanda> findByAtivoFalse();
}
