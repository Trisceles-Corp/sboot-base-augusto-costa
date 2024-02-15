package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.TipoService;
import br.com.augustocosta.acs.integration.entity.tblTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipo")
public class TipoController {

    @Autowired
    private TipoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblTipo", new tblTipo());
        return "tipo";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaTipos", service.getAll());
        model.addAttribute("tblTipo", new tblTipo());
        return "tipo";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblTipo tipo) {
        service.create(tipo);
        return "redirect:/tipo";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tblTipo", new tblTipo());
        return "tipo";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}