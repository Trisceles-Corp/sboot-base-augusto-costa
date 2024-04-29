package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblFormasPagamento ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormasPagamentoRepository extends JpaRepository<tblFormasPagamento , Integer> {
    List<tblFormasPagamento > findByAtivoTrueOrderByNomeAsc();
    List<tblFormasPagamento > findByAtivoTrue();
    List<tblFormasPagamento > findByAtivoFalse();
}
