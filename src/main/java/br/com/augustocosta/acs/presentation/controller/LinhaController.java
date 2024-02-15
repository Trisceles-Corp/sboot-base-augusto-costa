package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.LinhaService;
import br.com.augustocosta.acs.integration.entity.tblLinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("listaLinhas", service.getAll());
        model.addAttribute("tblLinha", new tblLinha());
        return "linha"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblLinha linha) {
        service.create(linha);
        return "redirect:/linha";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tblLinha", new tblLinha());
        return "linha";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}