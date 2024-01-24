package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCaracteristica;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracteristicaRepository extends JpaRepository<tblCaracteristica, Integer> {
    List<tblCaracteristica> findByDescricaoCaracteristica(String descricaoCaracteristica);
    List<tblCaracteristica> findByAtivoTrue();
    List<tblCaracteristica> findByAtivoFalse();
}
