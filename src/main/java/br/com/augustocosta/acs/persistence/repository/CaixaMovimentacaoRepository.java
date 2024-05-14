package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCaixaMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.List;

@Repository
public interface CaixaMovimentacaoRepository extends JpaRepository<tblCaixaMovimentacao , Integer> {
    List<tblCaixaMovimentacao> findByComandaId(Integer comandaId);
    List<tblCaixaMovimentacao> findByTipoMovimentacaoId(Integer tipoMovimentacaoId);
    List<tblCaixaMovimentacao> findByFormaPagamentoId(Integer formaPagamentoId);
    List<tblCaixaMovimentacao> findByAtivoTrueOrderByDataCriacaoDesc();
    List<tblCaixaMovimentacao> findByAtivoTrue();
    List<tblCaixaMovimentacao> findByAtivoFalse();
    List<tblCaixaMovimentacao> findByDataCriacaoBetweenAndAtivoTrueOrderByDataCriacaoDesc(LocalDateTime dataInicial, LocalDateTime dataFinal);
}
