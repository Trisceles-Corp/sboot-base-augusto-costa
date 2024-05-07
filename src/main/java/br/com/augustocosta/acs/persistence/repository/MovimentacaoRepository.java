package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblMarca;
import br.com.augustocosta.acs.integration.entity.tblMovimentacao;
import br.com.augustocosta.acs.integration.entity.tblTipoMovimentacao;
import br.com.augustocosta.acs.integration.entity.tblVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.*;

@Repository
public interface MovimentacaoRepository extends JpaRepository<tblMovimentacao, Integer> {
    List<tblMovimentacao> findByTipoMovimentacao(tblTipoMovimentacao tipoMovimentacao);
    List<tblMovimentacao> findByVenda(tblVenda venda);
    List<tblMovimentacao> findByNrNotaFiscal(Integer nrNotaFiscal);
    List<tblMovimentacao> findByDataCriacao(LocalDateTime dataCriacao);
    List<tblMovimentacao> findByAtivoTrue();
    List<tblMovimentacao> findByAtivoFalse();
    Integer findTopByOrderByNrNotaFiscalDesc();
}
