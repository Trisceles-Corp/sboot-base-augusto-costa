package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalEstoqueRepository extends JpaRepository<tblLocalEstoque, Integer> {
    List<tblLocalEstoque> findByDescricaoLocal(String DescricaoLocal);
    List<tblLocalEstoque> findByAtivoTrueOrderByDescricaoLocalAsc();
    List<tblLocalEstoque> findByAtivoTrue();
    List<tblLocalEstoque> findByAtivoFalse();
}

