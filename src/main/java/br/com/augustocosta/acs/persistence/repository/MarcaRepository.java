package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblMarca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<tblMarca, Integer> {
    List<tblMarca> findByDescricaoMarca(String DescricaoMarca);
    List<tblMarca> findByAtivoTrue();
    List<tblMarca> findByAtivoFalse();
}
