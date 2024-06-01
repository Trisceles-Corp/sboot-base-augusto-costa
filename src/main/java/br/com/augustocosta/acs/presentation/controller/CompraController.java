package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.integration.dto.dtoCompra;
import br.com.augustocosta.acs.integration.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService service;

    @Autowired
    private CompraProdutoService compraProdutoService;

    @Autowired
    private LocalEstoqueService localService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private SituacaoCompraService situacaoService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dtoCompra", new dtoCompra());
        return "compra";
    }

    @GetMapping
    public String listarCompras(Model model) {
        model.addAttribute("listarCompra", service.getActiveEstoqueFalse());
        model.addAttribute("listarProdutos", produtoService.getActives());
        model.addAttribute("listarCompraProdutos", compraProdutoService.getByCompra(0));
        model.addAttribute("listarLocalEstoque", localService.getActiveByNameAsc());
        model.addAttribute("listarSituacao", situacaoService.getActiveByNameAsc());
        model.addAttribute("dtoCompra", new dtoCompra());
        return "compra";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute dtoCompra dados) {
        List<tblCompraProduto> tableCompras = dados.getCompraProdutos();
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;

        if (dados.getId() != null && dados.getId() != 0){
            tblCompra table = service.getById(dados.getId()).orElseThrow();
            table.setAtivo(true);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.update(table, tableCompras);
        }
        else {
            tblCompra table = new tblCompra();
            tblLocalEstoque localEstoque = localService.getById(dados.getLocalEstoqueId()).orElseThrow();
            tblSituacaoCompra situacaoCompra = situacaoService.getById(dados.getSituacaoCompraId()).orElseThrow();
            table.setLocalEstoque(localEstoque);
            table.setSituacaoCompra(situacaoCompra);
            table.setValorTotal(dados.getValorTotal());
            table.setEstoque(false);
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(activeUserId);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.create(table, tableCompras);
        }
        return "redirect:/index?origem=compra";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index?origem=compra";
    }

    @GetMapping("/produtos/{compraId}")
    @ResponseBody
    public ResponseEntity<List<tblCompraProduto>> listarProdutosPorCompra(@PathVariable Integer compraId) {
        List<tblCompraProduto> produtos = compraProdutoService.getByCompra(compraId);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/listaCompraProdutos/{produtoId}")
    @ResponseBody
    public ResponseEntity<tblProduto> listaCompraProdutos(@PathVariable("produtoId") Integer produtoId) {
        tblProduto produto = produtoService.getById(produtoId).orElseThrow();
        return ResponseEntity.ok(produto);
    }

}