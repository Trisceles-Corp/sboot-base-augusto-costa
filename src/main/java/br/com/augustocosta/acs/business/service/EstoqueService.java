package br.com.augustocosta.acs.business.service;

import br.com.augustocosta.acs.integration.dto.dtoInventario;
import br.com.augustocosta.acs.integration.projections.prjInventario;
import br.com.augustocosta.acs.integration.entity.tblEstoque;
import br.com.augustocosta.acs.integration.entity.tblProduto;
import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import br.com.augustocosta.acs.integration.entity.tblMovimentacao;
import br.com.augustocosta.acs.persistence.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;

    @Autowired
    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    public tblEstoque create(tblEstoque table) {
        table.setDataCriacao(LocalDateTime.now());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAtivo(true);
        return repository.save(table);
    }

    public List<tblEstoque> getAll() {
        return repository.findAll();
    }

    public Optional<tblEstoque> getById(Integer id) {
        return repository.findById(id);
    }

    public List<tblEstoque> getByProduto(tblProduto produto) {
        return repository.findByProduto(produto);
    }

    public List<tblEstoque> getByLocalEstoque(tblLocalEstoque localEstoque) {
        return repository.findByLocalEstoque(localEstoque);
    }

    public List<tblEstoque> getByMovimentacao(tblMovimentacao movimentacao) {
        return repository.findByMovimentacao(movimentacao);
    }

    public List<dtoInventario> getByInventario() {
        List<prjInventario> projections = repository.findByInventario();
        return convertProjectionToDto(projections);
    }

    public List<tblEstoque> getActives() {
        return repository.findByAtivoTrue();
    }

    public List<tblEstoque> getInactives() {
        return repository.findByAtivoFalse();
    }

    public tblEstoque update(Integer id, tblEstoque dados) {
        tblEstoque table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com id: " + id));

        table.setProduto(dados.getProduto());
        table.setLocalEstoque(dados.getLocalEstoque());
        table.setMovimentacao(dados.getMovimentacao());
        table.setQuantidade(dados.getQuantidade());
        table.setValorMovimentacao(dados.getValorMovimentacao());
        table.setAtivo(dados.getAtivo());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(dados.getAlteradoPor());

        return repository.save(table);
    }

    public void delete(Integer id, int alteradoPor) {
        tblEstoque table = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado com id: " + id));

        table.setAtivo(false);
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(alteradoPor);

        repository.save(table);
    }

    public boolean isAtivo(Integer id) {
        Optional<tblEstoque> table = repository.findById(id);
        return table.map(tblEstoque::getAtivo).orElse(false);
    }

    public List<dtoInventario> convertProjectionToDto(List<prjInventario> projections) {
        List<dtoInventario> dtos = new ArrayList<>();
        for(prjInventario prj : projections) {
            dtoInventario dto = new dtoInventario();
            dto.setLocalEstoqueId(prj.getLocalEstoqueId());
            dto.setDescricaoLocal(prj.getDescricaoLocal());
            dto.setProdutoId(prj.getProdutoId());
            dto.setCodigoInterno(prj.getCodigoInterno());
            dto.setDescricaoProduto(prj.getDescricaoProduto());
            dto.setQtdProduto(prj.getQtdProduto());
            dto.setValorProduto(prj.getValorProduto());
            dtos.add(dto);
        }
        return dtos;
    }
}