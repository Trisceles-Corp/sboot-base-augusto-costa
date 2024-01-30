package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.tblProduto;
import br.com.augustocosta.acs.integration.entity.tblMarca;
import br.com.augustocosta.acs.integration.entity.tblLinha;
import br.com.augustocosta.acs.integration.entity.tblCaracteristica;
import br.com.augustocosta.acs.persistence.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public tblProduto create(tblProduto table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public List<tblProduto> getAll() {
        return repository.findAll();
    }

    public Optional<tblProduto> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblProduto> getByCodigoInterno(Integer codigoInterno) {
        return repository.findByCodigoInterno(codigoInterno);
    }

    public List<tblProduto> getByDescricao(String descricaoProduto) {
        return repository.findByDescricaoProduto(descricaoProduto);
    }

    public List<tblProduto> getByMarca(tblMarca marca) {
        return repository.findByMarca(marca);
    }

    public List<tblProduto> getByLinha(tblLinha linha) {
        return repository.findByLinha(linha);
    }

    public List<tblProduto> getByCaracteristica(tblCaracteristica caracteristica) {
        return repository.findByCaracteristica(caracteristica);
    }

    public List<tblProduto> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblProduto> getInactives() {
        return repository.findByAtivoFalse();
    }

    public tblProduto update(Integer id, tblProduto dados) {
        tblProduto table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com id: " + id));

        table.setCodigoInterno(dados.getCodigoInterno());
        table.setDescricaoProduto(dados.getDescricaoProduto());
        table.setCodigoBarras(dados.getCodigoBarras());
        table.setCategoria(dados.getCategoria());
        table.setEstoqueMinimo(dados.getEstoqueMinimo());
        table.setCusto(dados.getCusto());
        table.setValorVenda(dados.getValorVenda());
        table.setComissao(dados.getComissao());
        table.setMarca(dados.getMarca());
        table.setLinha(dados.getLinha());
        table.setCaracteristica(dados.getCaracteristica());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    public void delete(Integer id, int alteradoPor) {
        tblProduto table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblProduto> table = repository.findById(id);
        return table.map(tblProduto::getAtivo).orElse(false);
    }
}