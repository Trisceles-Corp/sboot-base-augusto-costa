package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblTipo;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoRepository extends JpaRepository<tblTipo, Integer> {
    List<tblTipo> findByDescricao(String descricao);
    List<tblTipo> findByAtivoTrueOrderByDescricaoAsc();
    List<tblTipo> findByAtivoTrue();
    List<tblTipo> findByAtivoFalse();
}
