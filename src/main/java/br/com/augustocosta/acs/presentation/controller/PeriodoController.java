package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.PeriodoService;
import br.com.augustocosta.acs.integration.entity.tblPeriodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/periodo")
public class PeriodoController {

    @Autowired
    private PeriodoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblPeriodo", new tblPeriodo());
        return "periodo";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaPeriodos", service.getAll());
        model.addAttribute("tblPeriodo", new tblPeriodo());
        return "periodo"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblPeriodo periodo) {
        service.create(periodo);
        return "redirect:/periodo";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tblPeriodo", new tblPeriodo());
        return "periodo";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}