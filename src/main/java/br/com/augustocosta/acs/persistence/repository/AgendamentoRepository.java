package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblAgendamento;
import br.com.augustocosta.acs.integration.entity.tblSituacaoAgendamento;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AgendamentoRepository extends JpaRepository<tblAgendamento, Integer> {
    List<tblAgendamento> findByColaborador(tblUsuario colaborador);
    List<tblAgendamento> findByCliente(tblUsuario cliente);
    List<tblAgendamento> findBySituacao(tblSituacaoAgendamento situacao);
    List<tblAgendamento> findByDataAgendamento(Date dataAgendamento);
    List<tblAgendamento> findByAtivoTrue();
    List<tblAgendamento> findByAtivoFalse();
}
