package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.CategoriaService;
import br.com.augustocosta.acs.integration.entity.tblCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categoria")
public class categoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblCategoria", new tblCategoria());
        return "categoria";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaCategorias", service.getAll());
        model.addAttribute("tblCategoria", new tblCategoria());
        return "categoria";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblCategoria categoria) {
        service.create(categoria);
        return "redirect:/categoria";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tblCategoria", new tblCategoria());
        return "categoria";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}