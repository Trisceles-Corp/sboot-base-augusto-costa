package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.EstoqueService;
import br.com.augustocosta.acs.integration.dto.dtoInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dtoInventario", new dtoInventario());
        return "estoque";
    }

    @GetMapping
    public String listarEstoque(Model model) {
        model.addAttribute("listarEstoque", service.getByInventario());
        model.addAttribute("dtoInventario", new dtoInventario());
        return "estoque";
    }
}