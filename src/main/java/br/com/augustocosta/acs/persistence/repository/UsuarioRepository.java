package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<tblUsuario, Integer> {
    List<tblUsuario> findByEmail(String email);
    List<tblUsuario> findByIdAndAtivo(Integer id, Boolean ativo);
    List<tblUsuario> findByAtivoTrue();
    List<tblUsuario> findByAtivoFalse();
}
