package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblAgendamento;
import br.com.augustocosta.acs.integration.entity.tblBloqueio;
import br.com.augustocosta.acs.integration.entity.tblServico;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AgendamentoRepository extends JpaRepository<tblAgendamento, Integer> {
    List<tblAgendamento> findByColaborador(tblUsuario colaborador);
    List<tblAgendamento> findByCliente(tblUsuario cliente);
    List<tblAgendamento> findByServico(tblServico servico);
    List<tblAgendamento> findByBloqueio(tblBloqueio bloqueio);
    List<tblAgendamento> findByDataAgendamento(Date dataAgendamento);
    List<tblAgendamento> findByAtivoTrue();
    List<tblAgendamento> findByAtivoFalse();
}
