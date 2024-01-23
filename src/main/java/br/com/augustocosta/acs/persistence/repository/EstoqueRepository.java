package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblEstoque;
import br.com.augustocosta.acs.integration.entity.tblMovimentacao;
import br.com.augustocosta.acs.integration.entity.tblProduto;
import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<tblEstoque, Integer> {
    List<tblEstoque> findByNome(String nome);
    List<tblEstoque> findByProduto(tblProduto produto);
    List<tblEstoque> findByLocalEstoque(tblLocalEstoque localEstoque);
    List<tblEstoque> findByMovimentacao(tblMovimentacao movimentacao);
}
