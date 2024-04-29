package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.CompraProdutoService;
import br.com.augustocosta.acs.business.service.CompraService;
import br.com.augustocosta.acs.business.service.LocalEstoqueService;
import br.com.augustocosta.acs.business.service.SituacaoCompraService;
import br.com.augustocosta.acs.integration.entity.tblCompraProduto;
import br.com.augustocosta.acs.integration.entity.tblCompra;
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
    private SituacaoCompraService situacaoService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblCompra", new tblCompra());
        model.addAttribute("tblCompraProduto", new tblCompraProduto());
        return "compra";
    }

    @GetMapping
    public String listarCompras(Model model) {
        model.addAttribute("listarCompra", service.getActives());
        model.addAttribute("listarCompraProdutos", compraProdutoService.getByCompra(0));
        model.addAttribute("listarLocalEstoque", localService.getActiveByNameAsc());
        model.addAttribute("listarSituacao", situacaoService.getActiveByNameAsc());
        model.addAttribute("tblCompra", new tblCompra());
        model.addAttribute("tblCompraProduto", new tblCompraProduto());
        return "compra";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblCompra table) {
        table.setAtivo(true);
        if (table.getId() != null && table.getId() != 0){
            Optional<tblCompra> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.update(table);
        }
        else {
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(1);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.create(table);
        }

        return "redirect:/index";
    }

    @GetMapping("/novo")
    public String novoSituacaoAgendamento(Model model) {
        model.addAttribute("listarCompra", service.getActives());
        model.addAttribute("listarCompraProdutos", compraProdutoService.getActives());
        model.addAttribute("tblCompra", new tblCompra());
        model.addAttribute("tblCompraProduto", new tblCompraProduto());
        return "compra";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }

    @GetMapping("/produtos/{compraId}")
    @ResponseBody
    public ResponseEntity<List<tblCompraProduto>> listarProdutosPorCompra(@PathVariable Integer compraId) {
        List<tblCompraProduto> produtos = compraProdutoService.getByCompra(compraId);
        return ResponseEntity.ok(produtos);
    }
}