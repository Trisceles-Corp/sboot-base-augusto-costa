package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.entity.tblCaixa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblCaixa", new tblCaixa());
        return "caixa";
    }

    @GetMapping
    public String listarDependencias(Model model) {
        model.addAttribute("listarCaixas", service.getActiveByNameAsc());
        model.addAttribute("tblCaixa", new tblCaixa());
        return "caixa";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblCaixa table) {
        table.setAtivo(true);

        if (table.getId() != null && table.getId() != 0){
            tblCaixa data = service.getByCaixaId(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.getDataCriacao());
            table.setCriadoPor(data.getCriadoPor());
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
        model.addAttribute("listarCaixas", service.getActiveByNameAsc());
        model.addAttribute("tblCaixa", new tblCaixa());
        return "caixa";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }
}