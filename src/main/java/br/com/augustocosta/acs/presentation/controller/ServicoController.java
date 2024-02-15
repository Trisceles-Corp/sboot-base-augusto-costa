package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.ServicoService;
import br.com.augustocosta.acs.integration.entity.tblServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblServico", new tblServico());
        return "servico";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaServicos", service.getAll());
        model.addAttribute("tblServico", new tblServico());
        return "servico"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblServico servico) {
        service.create(servico);
        return "redirect:/servico";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tblServico", new tblServico());
        return "servico";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}