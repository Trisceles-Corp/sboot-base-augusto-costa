package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblComanda;
import br.com.augustocosta.acs.integration.entity.tblFormasPagamento;
import br.com.augustocosta.acs.integration.entity.tblComandaPagamento;
import br.com.augustocosta.acs.integration.entity.tblBandeiras ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComandaPagamentoRepository extends JpaRepository<tblComandaPagamento , Integer> {
    List<tblComandaPagamento> findByComanda(tblComanda comanda);
    List<tblComandaPagamento> findByFormaPagamento(tblFormasPagamento forma);
    List<tblComandaPagamento> findByBandeira(tblBandeiras bandeira);
    List<tblComandaPagamento> findByAtivoTrue();
    List<tblComandaPagamento> findByAtivoFalse();
}
