package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCompra;
import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import br.com.augustocosta.acs.integration.entity.tblSituacaoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<tblCompra , Integer> {
    List<tblCompra> findByLocalEstoque(tblLocalEstoque localEstqoue);
    List<tblCompra> findBySituacaoCompra(tblSituacaoCompra situacaoCompra);
    List<tblCompra > findByAtivoTrue();
    List<tblCompra > findByAtivoFalse();
}
