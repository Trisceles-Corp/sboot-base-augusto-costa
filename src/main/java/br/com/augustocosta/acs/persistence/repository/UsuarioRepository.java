package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCargo;
import br.com.augustocosta.acs.integration.entity.tblPerfil;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<tblUsuario, Integer> {
    List<tblUsuario> findByNome(String nome);
    List<tblUsuario> findBySobrenome(String sobrenome);
    List<tblUsuario> findByAtivoTrueOrderByNomeAsc();
    List<tblUsuario> findByCpf(Double cpf);
    List<tblUsuario> findByEmail(String email);
    List<tblUsuario> findByCargo(tblCargo cargo);
    List<tblUsuario> findByPerfil(tblPerfil perfil);
    List<tblUsuario> findByAtivoTrue();
    List<tblUsuario> findByAtivoFalse();
}
