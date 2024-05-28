package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.dto.dtoCaixa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "redirect:/index?origem=caixa";
    }

    @GetMapping("/novo")
    public String novoSituacaoAgendamento(Model model) {
        model.addAttribute("listarCaixas", service.getActiveByNameAsc());
        model.addAttribute("dtoCaixa", new dtoCaixa());
        return "caixa";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index?origem=caixa";
    }
}