package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.ServicosAgendamento;
import br.com.augustocosta.acs.integration.entity.tblServico;
import br.com.augustocosta.acs.integration.entity.tblServicosAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicosAgendamentoRepository extends JpaRepository<tblServicosAgendamento, ServicosAgendamento> {
    List<tblServicosAgendamento> findByAgendamentoId(Integer id);
    List<tblServicosAgendamento> findByAtivoTrue();
    List<tblServicosAgendamento> findByAtivoFalse();
}