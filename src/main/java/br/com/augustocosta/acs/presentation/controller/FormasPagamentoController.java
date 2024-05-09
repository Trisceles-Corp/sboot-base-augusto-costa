package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.entity.tblFormasPagamento;
import br.com.augustocosta.acs.integration.entity.tblProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/formaspagamento")
public class FormasPagamentoController {

    @Autowired
    private FormasPagamentoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblFormasPagamento", new tblFormasPagamento());
        return "formaspagamento";
    }

    @GetMapping
    public String listarFormasPagamentos(Model model) {
        model.addAttribute("listarFormasPagamentos", service.getActives());
        model.addAttribute("tblFormasPagamento", new tblFormasPagamento());
        return "formaspagamento";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblFormasPagamento table) {
        table.setAtivo(true);

        if (table.getId() != null && table.getId() != 0){
            Optional<tblFormasPagamento> data = service.getById(table.getId());
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
        model.addAttribute("listarFormasPagamentos", service.getActives());
        model.addAttribute("tblFormasPagamento", new tblFormasPagamento());
        return "formaspagamento";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }
}