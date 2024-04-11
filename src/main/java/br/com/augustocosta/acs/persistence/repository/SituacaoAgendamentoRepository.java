package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblSituacaoAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SituacaoAgendamentoRepository extends JpaRepository<tblSituacaoAgendamento, Integer> {
    List<tblSituacaoAgendamento> findByNome(String nome);
    List<tblSituacaoAgendamento> findByAtivoTrueOrderByNomeAsc();
    List<tblSituacaoAgendamento> findByAtivoTrue();
    List<tblSituacaoAgendamento> findByAtivoFalse();
}
