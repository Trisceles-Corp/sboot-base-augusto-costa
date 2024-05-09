package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCaixaMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaixaMovimentacaoRepository extends JpaRepository<tblCaixaMovimentacao , Integer> {
    List<tblCaixaMovimentacao> findByAtivoTrue();
    List<tblCaixaMovimentacao> findByAtivoFalse();
}
