package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblPerfil;
import br.com.augustocosta.acs.integration.entity.tblTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilRepository extends JpaRepository<tblPerfil, Integer> {
    List<tblPerfil> findByNome(String nome);
    List<tblPerfil> findByAtivoTrueOrderByNomeAsc();
    List<tblPerfil> findByTipo(tblTipo tipo);
    List<tblPerfil> findByAtivoTrue();
    List<tblPerfil> findByAtivoFalse();
}
