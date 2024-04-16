package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.VendaProduto;
import br.com.augustocosta.acs.integration.entity.tblVenda;
import br.com.augustocosta.acs.integration.entity.tblProduto ;
import br.com.augustocosta.acs.integration.entity.tblVendaProduto ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendaProdutoRepository extends JpaRepository<tblVendaProduto , Integer> {
    List<tblVendaProduto> findByVenda(tblVenda venda);
    List<tblVendaProduto> findByProduto(tblProduto produto);
    List<tblVendaProduto> findByAtivoTrue();
    List<tblVendaProduto> findByAtivoFalse();
    tblVendaProduto getReferenceById(VendaProduto id);
    void deleteById(VendaProduto id);
}
