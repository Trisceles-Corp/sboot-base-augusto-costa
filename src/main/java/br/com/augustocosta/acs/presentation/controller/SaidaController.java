package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.dto.dtoSaida;
import br.com.augustocosta.acs.integration.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/saida")
public class SaidaController {

    @Autowired
    private SaidaService service;

    @Autowired
    private SaidaProdutoService saidaProdutoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LocalEstoqueService localService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dtoSaida", new dtoSaida());
        return "saida";
    }

    @GetMapping
    public String listarSaidas(Model model) {
        model.addAttribute("listarSaidas", service.getActives());
        model.addAttribute("listarProdutos", produtoService.getActives());
        model.addAttribute("listarSaidaProdutos", saidaProdutoService.getBySaida(0));
        model.addAttribute("listarLocalEstoque", localService.getActiveByNameAsc());
        model.addAttribute("listarSolicitante", usuarioService.getAllBySolicitante());
        model.addAttribute("dtoSaida", new dtoSaida());
        return "saida";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute dtoSaida dados) {
        List<tblSaidaProduto> tableSaidas = dados.getSaidaProdutos();
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;

        if (dados.getId() != null && dados.getId() != 0){
            tblSaida table = service.getById(dados.getId()).orElseThrow();
            table.setAtivo(true);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.update(table, tableSaidas);
        }
        else {
            tblSaida table = new tblSaida();
            tblLocalEstoque localEstoque = localService.getById(dados.getLocalEstoqueId()).orElseThrow();
            tblUsuario solicitante = usuarioService.getById(dados.getSolicitanteId()).orElseThrow();
            table.setLocalEstoque(localEstoque);
            table.setSolicitante(solicitante);
            table.setValorTotal(dados.getValorTotal());
            table.setEstoque(false);
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(activeUserId);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.create(table, tableSaidas);
        }
        return "redirect:/index?origem=saida";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index?origem=saida";
    }

    @GetMapping("/produtos/{saidaId}")
    @ResponseBody
    public ResponseEntity<List<tblSaidaProduto>> listarProdutosPorSaida(@PathVariable Integer saidaId) {
        List<tblSaidaProduto> produtos = saidaProdutoService.getBySaida(saidaId);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/listaSaidaProdutos/{produtoId}")
    @ResponseBody
    public ResponseEntity<tblProduto> listaSaidaProdutos(@PathVariable("produtoId") Integer produtoId) {
        tblProduto produto = produtoService.getById(produtoId).orElseThrow();
        return ResponseEntity.ok(produto);
    }

}