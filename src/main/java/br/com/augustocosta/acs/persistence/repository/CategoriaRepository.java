package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<tblCategoria, Integer> {
    List<tblCategoria> findByNome(String Nome);
    List<tblCategoria> findByAtivoTrue();
    List<tblCategoria> findByAtivoTrueOrderByNomeAsc();
    List<tblCategoria> findByAtivoFalse();
}
