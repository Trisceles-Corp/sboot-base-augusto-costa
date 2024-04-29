package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import br.com.augustocosta.acs.integration.entity.tblSaida ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaidaRepository extends JpaRepository<tblSaida , Integer> {
    List<tblSaida> findByLocalEstoque(tblLocalEstoque localEstqoue);
    List<tblSaida> findBySolicitante(tblUsuario solicitante);
    List<tblSaida > findByAtivoTrue();
    List<tblSaida > findByAtivoFalse();
}
