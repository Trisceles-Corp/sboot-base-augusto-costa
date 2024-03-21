package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.dto.dtoUsuario;
import br.com.augustocosta.acs.persistence.dao.SqlQueries;
import org.springframework.data.jpa.repository.Query;
import br.com.augustocosta.acs.integration.entity.tblCargo;
import br.com.augustocosta.acs.integration.entity.tblPerfil;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
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
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL, nativeQuery = true)
    List<dtoUsuario> findUsersByPerfilId(@Param("perfilId") Integer perfilId);
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL_CPF, nativeQuery = true)
    List<dtoUsuario> findUsersByPerfilIdAndCpf(@Param("perfilId") Integer perfilId, @Param("cpf") Double cpf);
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL_EMAIL, nativeQuery = true)
    List<dtoUsuario> findUsersByPerfilIdAndEmail(@Param("perfilId") Integer perfilId, @Param("email") String email);
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL_NOME, nativeQuery = true)
    List<dtoUsuario> findUsersByPerfilIdAndNome(@Param("perfilId") Integer perfilId, @Param("nome") String nome);
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL_SOBRENOME, nativeQuery = true)
    List<dtoUsuario> findUsersByPerfilIdAndSobrenome(@Param("perfilId") Integer perfilId, @Param("sobrenome") String sobrenome);
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL_CELULAR, nativeQuery = true)
    List<dtoUsuario> findUsersByPerfilIdAndCelular(@Param("perfilId") Integer perfilId, @Param("celular") Double celular);
}
