package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    Optional<Tipo> findByDesc(String descricao);
}
