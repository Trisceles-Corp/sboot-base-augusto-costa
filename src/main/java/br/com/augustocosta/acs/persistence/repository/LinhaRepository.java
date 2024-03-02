package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblLinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinhaRepository extends JpaRepository<tblLinha, Integer> {
    List<tblLinha> findByDescricaoLinha(String DescricaoLinha);
    List<tblLinha> findByAtivoTrueOrderByDescricaoLinhaAsc();
    List<tblLinha> findByAtivoTrue();
    List<tblLinha> findByAtivoFalse();
}
