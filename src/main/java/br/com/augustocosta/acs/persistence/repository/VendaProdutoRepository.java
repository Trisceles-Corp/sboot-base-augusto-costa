package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.VendasProduto;
import br.com.augustocosta.acs.integration.entity.tblVendaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaProdutoRepository extends JpaRepository<tblVendaProduto, VendasProduto> {

}