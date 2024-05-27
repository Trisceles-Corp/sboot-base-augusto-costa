package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.LinhaService;
import br.com.augustocosta.acs.integration.entity.tblLinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/linha")
public class LinhaController {

    @Autowired
    private LinhaService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblLinha", new tblLinha());
        return "linha";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaLinhas", service.getActiveByNameAsc());
        model.addAttribute("tblLinha", new tblLinha());
        return "linha";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblLinha table) {
        table.setAtivo(true);
        if (table.getId() != null && table.getId() != 0){
            Optional<tblLinha> data = service.getById(table.getId());
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
        return "redirect:/index?origem=linha";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listaLinhas", service.getActiveByNameAsc());
        model.addAttribute("tblLinha", new tblLinha());
        return "linha";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index?origem=linha";
    }
}