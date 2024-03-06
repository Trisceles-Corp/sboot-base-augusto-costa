package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblTipoPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoPerfilRepository extends JpaRepository<tblTipoPerfil, Integer> {
    List<tblTipoPerfil> findByDescricao(String descricao);
    List<tblTipoPerfil> findByAtivoTrueOrderByDescricaoAsc();
    List<tblTipoPerfil> findByAtivoTrue();
    List<tblTipoPerfil> findByAtivoFalse();
}
