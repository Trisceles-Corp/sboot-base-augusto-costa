package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblCargo;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<tblCargo, Integer> {
    List<tblCargo> findByNome(String nome);
    List<tblCargo> findByAtivoTrueOrderByNomeAsc();
    List<tblCargo> findByAtivoTrue();
    List<tblCargo> findByAtivoFalse();
}
