package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.PermissoesService;
import br.com.augustocosta.acs.integration.entity.tblPermissoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/permissoes")
public class PermissoesController {

    @Autowired
    private PermissoesService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblPermissoes", new tblPermissoes());
        return "permissoes";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("permissoes", service.getAll());
        model.addAttribute("permissoes", new tblPermissoes());
        return "permissoes"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblPermissoes permissoes) {
        service.create(permissoes);
        return "redirect:/permissoes";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("permissoes", new tblPermissoes());
        return "permissoes";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}