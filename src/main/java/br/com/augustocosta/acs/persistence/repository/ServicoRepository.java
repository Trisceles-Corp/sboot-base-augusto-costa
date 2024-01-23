package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.*;

@Repository
public interface ServicoRepository extends JpaRepository<tblServico, Integer> {
    List<tblServico> findByNome(String Nome);
    List<tblServico> findByAtivoTrue();
    List<tblServico> findByAtivoFalse();
}
