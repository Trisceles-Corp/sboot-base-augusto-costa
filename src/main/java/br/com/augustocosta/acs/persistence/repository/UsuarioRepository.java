package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCargo;
import br.com.augustocosta.acs.integration.entity.tblPerfil;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<tblUsuario, Integer> {
    List<tblUsuario> findByNomeOrderByNomeAsc(String nome);
    List<tblUsuario> findBySobrenomeOrderByNomeAsc(String sobrenome);
    List<tblUsuario> findByAtivoTrueOrderByNomeAsc();
    List<tblUsuario> findByCpfOrderByNomeAsc(String cpf);
    List<tblUsuario> findByEmailOrderByNomeAsc(String email);
    List<tblUsuario> findByCargoOrderByNomeAsc(tblCargo cargo);
    List<tblUsuario> findByPerfilOrderByNomeAsc(tblPerfil perfil);
    List<tblUsuario> findByPerfilId(Integer perfilId);
    List<tblUsuario> findByAtivoTrue();
    List<tblUsuario> findByAtivoFalse();
}
