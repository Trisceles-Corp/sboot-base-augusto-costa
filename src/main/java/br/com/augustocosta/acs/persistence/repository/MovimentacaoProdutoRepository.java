package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblMovimentacaoProduto;
import br.com.augustocosta.acs.integration.entity.MovimentacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoProdutoRepository extends JpaRepository<tblMovimentacaoProduto, MovimentacaoProduto> {

}