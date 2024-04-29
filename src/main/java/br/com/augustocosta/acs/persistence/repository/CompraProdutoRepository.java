package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCompra;
import br.com.augustocosta.acs.integration.entity.tblProduto ;
import br.com.augustocosta.acs.integration.entity.tblCompraProduto ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraProdutoRepository extends JpaRepository<tblCompraProduto , Integer> {
    List<tblCompraProduto> findByCompra(tblCompra compra);
    List<tblCompraProduto> findByProduto(tblProduto produto);
    List<tblCompraProduto> findByAtivoTrue();
    List<tblCompraProduto> findByAtivoFalse();
}
