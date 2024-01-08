package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    Optional<Perfil> findByName(String nome);
}
