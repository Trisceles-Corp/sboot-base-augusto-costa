package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblPermissoesPerfil;
import br.com.augustocosta.acs.integration.entity.PermissoesPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissoesPerfilRepository extends JpaRepository<tblPermissoesPerfil, PermissoesPerfil> {

}