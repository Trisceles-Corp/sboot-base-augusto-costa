package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.ComandaService;
import br.com.augustocosta.acs.integration.entity.tblComanda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/comanda")
public class ComandaController {

    @Autowired
    private ComandaService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblComanda", new tblComanda());
        return "comanda";
    }

    @GetMapping
    public String listarComandas(Model model) {
        model.addAttribute("listarComandas", service.getActives());
        model.addAttribute("tblComanda", new tblComanda());
        return "comanda";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblComanda table) {
        table.setAtivo(true);
        if (table.getId() != null && table.getId() != 0){
            Optional<tblComanda> data = service.getById(table.getId());
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
        model.addAttribute("listarComandas", service.getActives());
        model.addAttribute("tblComanda", new tblComanda());
        return "comanda";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }
}