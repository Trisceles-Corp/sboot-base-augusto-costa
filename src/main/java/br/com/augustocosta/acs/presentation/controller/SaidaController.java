package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.entity.tblCompraProduto;
import br.com.augustocosta.acs.integration.entity.tblSaida;
import br.com.augustocosta.acs.integration.entity.tblSaidaProduto;
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
    private LocalEstoqueService localService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblSaida", new tblSaida());
        return "saida";
    }

    @GetMapping
    public String listarSaidas(Model model) {
        model.addAttribute("listarSaidas", service.getActives());
        model.addAttribute("listarSaidaProdutos", saidaProdutoService.getBySaida(0));
        model.addAttribute("listarLocalEstoque", localService.getActiveByNameAsc());
        model.addAttribute("listarSolicitante", usuarioService.getAllBySolicitante());
        model.addAttribute("tblSaida", new tblSaida());
        return "saida";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblSaida table) {
        table.setAtivo(true);
        if (table.getId() != null && table.getId() != 0){
            Optional<tblSaida> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.update(table.getId(), table);
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
        model.addAttribute("listarSaidas", service.getActives());
        model.addAttribute("listarLocalEstoque", localService.getActiveByNameAsc());
        model.addAttribute("listarSolicitante", usuarioService.getAllBySolicitante());
        model.addAttribute("tblSaida", new tblSaida());
        return "saida";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }

    @GetMapping("/produtos/{saidaId}")
    @ResponseBody
    public ResponseEntity<List<tblSaidaProduto>> listarProdutosPorSaida(@PathVariable Integer saidaId) {
        List<tblSaidaProduto> produtos = saidaProdutoService.getBySaida(saidaId);
        return ResponseEntity.ok(produtos);
    }
}