package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.dto.dtoCaixa;
import br.com.augustocosta.acs.integration.entity.tblCaixa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/caixa")
public class CaixaController {

    @Autowired
    private CaixaService service;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dtoCaixa", new dtoCaixa());
        return "caixa";
    }

    @GetMapping
    public String listarDependencias(Model model) {
        model.addAttribute("listarCaixas", service.getAllByAtivo());
        model.addAttribute("listarColaboradores", usuarioService.getAllByPerfil(5));
        model.addAttribute("dtoCaixa", new dtoCaixa());
        return "caixa";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute dtoCaixa dados) {
        if (dados.getCaixaId() != null && dados.getCaixaId() != 0){
            service.update(dados);
        }
        else {
            service.create(dados);
        }
        return "redirect:/index";
    }

    @GetMapping("/novo")
    public String novoSituacaoAgendamento(Model model) {
        model.addAttribute("listarCaixas", service.getActiveByNameAsc());
        model.addAttribute("dtoCaixa", new dtoCaixa());
        return "caixa";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }
}