package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.LocalEstoqueService;
import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/localestoque")
public class LocalEstoqueController {

    @Autowired
    private LocalEstoqueService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblLocalEstoque", new tblLocalEstoque());
        return "localestoque";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listalocais", service.getAll());
        model.addAttribute("tblLocalEstoque", new tblLocalEstoque());
        return "localestoque"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblLocalEstoque localestoque) {
        service.create(localestoque);
        return "redirect:/localestoque";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tblLocalEstoque", new tblLocalEstoque());
        return "localestoque";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}