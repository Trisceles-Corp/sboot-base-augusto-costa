package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblSaida;
import br.com.augustocosta.acs.integration.entity.tblProduto ;
import br.com.augustocosta.acs.integration.entity.tblSaidaProduto ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaidaProdutoRepository extends JpaRepository<tblSaidaProduto , Integer> {
    List<tblSaidaProduto> findBySaida(tblSaida saida);
    List<tblSaidaProduto> findByProduto(tblProduto produto);
    List<tblSaidaProduto> findByAtivoTrue();
    List<tblSaidaProduto> findByAtivoFalse();
}
