package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaixaRepository extends JpaRepository<tblCaixa , Integer> {
    List<tblCaixa> findByNome(String nome);
    List<tblCaixa> findByAtivoTrueOrderByNomeAsc();
    List<tblCaixa> findByAtivoTrue();
    List<tblCaixa> findByAtivoFalse();
}
