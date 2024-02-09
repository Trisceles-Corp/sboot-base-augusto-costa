package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.MarcaService;
import br.com.augustocosta.acs.integration.entity.tblMarca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblMarca", new tblMarca());
        return "marca";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaMarcas", service.getAll());
        model.addAttribute("tblMarca", new tblMarca());
        return "marca"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblMarca marca) {
        service.create(marca);
        return "redirect:/marca";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tblMarca", new tblMarca());
        return "marca";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}