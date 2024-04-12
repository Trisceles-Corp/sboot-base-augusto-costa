package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblSituacaoCompra ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SituacaoCompraRepository extends JpaRepository<tblSituacaoCompra , Integer> {
    List<tblSituacaoCompra > findByNome(String nome);
    List<tblSituacaoCompra > findByAtivoTrueOrderByNomeAsc();
    List<tblSituacaoCompra > findByAtivoTrue();
    List<tblSituacaoCompra > findByAtivoFalse();
}
