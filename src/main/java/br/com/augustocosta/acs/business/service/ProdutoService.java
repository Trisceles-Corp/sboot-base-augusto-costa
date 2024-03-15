package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.entity.*;
import br.com.augustocosta.acs.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    private final MarcaRepository marcaRepository;

    private final LinhaRepository linhaRepository;

    private final CaracteristicaRepository caracteristicaRepository;

    private final UsuarioRepository usuarioRepository;


    @Autowired
    public ProdutoService(ProdutoRepository repository, MarcaRepository marcaRepository, LinhaRepository linhaRepository, CaracteristicaRepository caracteristicaRepository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.marcaRepository = marcaRepository;
        this.linhaRepository = linhaRepository;
        this.caracteristicaRepository = caracteristicaRepository;
        this.usuarioRepository = usuarioRepository;
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

    public tblUsuario getByUserId(Integer id) {
        return usuarioRepository.getReferenceById(id);
    }

    public List<tblProduto> getByCodigoInterno(Integer codigoInterno) {
        return repository.findByCodigoInterno(codigoInterno);
    }

    public List<tblProduto> getByName(String descricaoProduto) {
        return repository.findByDescricaoProduto(descricaoProduto);
    }

    public List<tblProduto> getActiveByNameAsc() {
        return repository.findByAtivoTrueOrderByDescricaoProdutoAsc();
    }

    public List<tblMarca> getActiveMarcaByNameAsc() {return marcaRepository.findByAtivoTrueOrderByDescricaoMarcaAsc();}

    public List<tblLinha> getActiveLinhaByNameAsc() {return linhaRepository.findByAtivoTrueOrderByDescricaoLinhaAsc();}

    public List<tblCaracteristica> getActiveCaracteristicaByNameAsc() {return caracteristicaRepository.findByAtivoTrueOrderByDescricaoCaracteristicaAsc();}

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

    public void delete(Integer id, Integer alteradoPor) {
        tblProduto table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com id: " + id));
        tblUsuario user = usuarioRepository.getReferenceById(alteradoPor);
        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(user);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblProduto> table = repository.findById(id);
        return table.map(tblProduto::getAtivo).orElse(false);
    }
}