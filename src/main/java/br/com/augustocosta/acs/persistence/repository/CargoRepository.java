package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    Optional<Cargo> findByName(String nome);
}
