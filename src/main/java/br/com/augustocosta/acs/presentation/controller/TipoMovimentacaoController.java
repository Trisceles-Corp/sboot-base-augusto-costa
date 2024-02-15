package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.TipoMovimentacaoService;
import br.com.augustocosta.acs.integration.entity.tblTipoMovimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipomovimentacao")
public class TipoMovimentacaoController {

    @Autowired
    private TipoMovimentacaoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblTipoMovimentacao", new tblTipoMovimentacao());
        return "tipomovimentacao";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaTipoMovimentacao", service.getAll());
        model.addAttribute("tblTipoMovimentacao", new tblTipoMovimentacao());
        return "tipomovimentacao"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblTipoMovimentacao tipomovimentacao) {
        service.create(tipomovimentacao);
        return "redirect:/tipomovimentacao";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tblTipoMovimentacao", new tblTipoMovimentacao());
        return "tipomovimentacao";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}