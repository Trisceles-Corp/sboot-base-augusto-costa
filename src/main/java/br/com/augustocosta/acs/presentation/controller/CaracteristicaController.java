package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.CaracteristicaService;
import br.com.augustocosta.acs.integration.entity.tblCaracteristica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/caracteristica")
public class CaracteristicaController {

    @Autowired
    private CaracteristicaService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblCaracteristica", new tblCaracteristica());
        return "caracteristica";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaCaracteristica", service.getAll());
        model.addAttribute("tblCaracteristica", new tblCaracteristica());
        return "caracteristica"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblCaracteristica caracteristica) {
        service.create(caracteristica);
        return "redirect:/caracteristica";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tblCaracteristica", new tblCaracteristica());
        return "caracteristica";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}