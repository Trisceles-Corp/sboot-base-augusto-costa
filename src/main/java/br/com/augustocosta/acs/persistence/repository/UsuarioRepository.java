package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.dto.dtoUsuario;
import br.com.augustocosta.acs.persistence.dao.SqlQueries;
import org.springframework.data.jpa.repository.Query;
import br.com.augustocosta.acs.integration.entity.tblCargo;
import br.com.augustocosta.acs.integration.entity.tblPerfil;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.augustocosta.acs.integration.projections.prjUsuario;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<tblUsuario, Integer> {
    @Query("SELECT e FROM tblEndereco e WHERE e.id = :id")
    tblUsuario findEnderecoByUsuarioById(Integer id);

    @Query("SELECT u FROM tblUsuario u WHERE u.id = :id")
    tblUsuario findByUsuarioById(Integer id);

    List<tblUsuario> findByEmail(String email);
    List<tblUsuario> findByNomeOrderByNomeAsc(String nome);
    List<tblUsuario> findBySobrenomeOrderByNomeAsc(String sobrenome);
    List<tblUsuario> findByAtivoTrueOrderByNomeAsc();
    List<tblUsuario> findByEmailOrderByNomeAsc(String email);
    List<tblUsuario> findByCargoOrderByNomeAsc(tblCargo cargo);
    List<tblUsuario> findByPerfilOrderByNomeAsc(tblPerfil perfil);
    List<tblUsuario> findByPerfilId(Integer perfilId);
    List<tblUsuario> findByAtivoTrue();
    List<tblUsuario> findByAtivoFalse();
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL, nativeQuery = true)
    List<prjUsuario> findUsersByPerfilId(@Param("perfilId") Integer perfilId);
    @Query(value = SqlQueries.QUERY_USUARIO_ALL_ACTIVE, nativeQuery = true)
    List<prjUsuario> findUsersByAllActive();
    @Query(value = SqlQueries.QUERY_USUARIO_SOLICITANTE, nativeQuery = true)
    List<prjUsuario> findUsersBySolicitante();
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL_CPF, nativeQuery = true)
    List<prjUsuario> findUsersByPerfilIdAndCpfCnpj(@Param("perfilId") Integer perfilId, @Param("cpfCnpj") Double cpf);
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL_EMAIL, nativeQuery = true)
    List<prjUsuario> findUsersByPerfilIdAndEmail(@Param("perfilId") Integer perfilId, @Param("email") String email);
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL_NOME, nativeQuery = true)
    List<prjUsuario> findUsersByPerfilIdAndNome(@Param("perfilId") Integer perfilId, @Param("nome") String nome);
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL_SOBRENOME, nativeQuery = true)
    List<prjUsuario> findUsersByPerfilIdAndSobrenome(@Param("perfilId") Integer perfilId, @Param("sobrenome") String sobrenome);
    @Query(value = SqlQueries.QUERY_USUARIO_BY_PERFIL_CELULAR, nativeQuery = true)
    List<prjUsuario> findUsersByPerfilIdAndCelular(@Param("perfilId") Integer perfilId, @Param("celular") Double celular);
}
