package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.DiasSemanaService;
import br.com.augustocosta.acs.integration.entity.tblDiasSemana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/diasSemana")
public class DiasSemanaController {

    @Autowired
    private DiasSemanaService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblDiasSemana", new tblDiasSemana());
        return "diasSemana";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("diasSemana", service.getAll());
        model.addAttribute("diasSemana", new tblDiasSemana());
        return "diasSemana"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblDiasSemana diasSemana) {
        service.create(diasSemana);
        return "redirect:/diasSemana";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("diasSemana", new tblDiasSemana());
        return "diasSemana";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}