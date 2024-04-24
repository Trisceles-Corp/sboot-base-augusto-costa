package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblBandeiras ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandeirasRepository extends JpaRepository<tblBandeiras , Integer> {
    List<tblBandeiras > findByAtivoTrueOrderByNomeAsc();
    List<tblBandeiras > findByAtivoTrue();
    List<tblBandeiras > findByAtivoFalse();
}
