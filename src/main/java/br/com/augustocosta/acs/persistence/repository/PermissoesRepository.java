package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblPermissoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.*;

@Repository
public interface PermissoesRepository extends JpaRepository<tblPermissoes, Integer> {
    List<tblPermissoes> findByNome(String nome);
    List<tblPermissoes> findByAtivoTrueOrderByNomeAsc();
    List<tblPermissoes> findByAtivoTrue();
    List<tblPermissoes> findByAtivoFalse();
}
