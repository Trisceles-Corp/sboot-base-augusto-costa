package br.com.augustocosta.acs.persistence.repository;

import br.com.augustocosta.acs.integration.entity.tblEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<tblEndereco, Integer> {
    List<tblEndereco> findByCep(String cep);
    List<tblEndereco> findByLogradouro(String logradouro);
    List<tblEndereco> findByBairro(String bairro);
    List<tblEndereco> findByCidade(String cidade);
    List<tblEndereco> findByUf(String uf);
    List<tblEndereco> findByAtivoTrueOrderByLogradouroAsc();
    List<tblEndereco> findByAtivoTrue();
    List<tblEndereco> findByAtivoFalse();
}
