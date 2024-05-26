package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.DiasSemanaService;
import br.com.augustocosta.acs.integration.entity.tblDiasSemana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

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
        model.addAttribute("listaDiasSemana", service.getAll());
        model.addAttribute("tblDiasSemana", new tblDiasSemana());
        return "diasSemana"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblDiasSemana table) {
        if (table.getId() != null && table.getId() != 0){
            Optional<tblDiasSemana> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.update(table.getId(), table);
        }
        else {
            table.setAtivo(true);
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(1);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.create(table);
        }

        return "redirect:/index";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listaDiasSemana", service.getAll());
        model.addAttribute("tblDiasSemana", new tblDiasSemana());
        return "diasSemana";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}