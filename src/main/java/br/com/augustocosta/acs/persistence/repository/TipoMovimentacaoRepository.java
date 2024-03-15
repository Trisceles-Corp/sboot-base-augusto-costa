package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblTipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.*;

@Repository
public interface TipoMovimentacaoRepository extends JpaRepository<tblTipoMovimentacao, Integer> {
    List<tblTipoMovimentacao> findByDescricaoMovimentacao(String descricaoMovimentacao);
    List<tblTipoMovimentacao> findByAtivoTrueOrderByDescricaoMovimentacaoAsc();
    List<tblTipoMovimentacao> findByAtivoTrue();
    List<tblTipoMovimentacao> findByAtivoFalse();
}
