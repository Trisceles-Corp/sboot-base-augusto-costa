package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import br.com.augustocosta.acs.integration.entity.tblMarca;
import br.com.augustocosta.acs.integration.entity.tblVenda;
import br.com.augustocosta.acs.integration.entity.tblAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.*;

@Repository
public interface VendaRepository extends JpaRepository<tblVenda, Integer> {
    List<tblVenda> findByAgendamentoId(Integer id);
    List<tblVenda> findByLocalEstoque(tblLocalEstoque localEstoque);
    List<tblVenda> findByDataCriacao(LocalDateTime dataCriacao);
    List<tblVenda> findByAtivoTrue();
    List<tblVenda> findByAtivoFalse();
}
