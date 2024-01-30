package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblVenda;
import br.com.augustocosta.acs.integration.entity.tblAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.*;

@Repository
public interface VendaRepository extends JpaRepository<tblVenda, Integer> {
    List<tblVenda> findByAgendamento(tblAgendamento agendamento);
}
