package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.entity.tblFormasPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/formaspagamento")
public class FormasPagamentoController {

    @Autowired
    private FormasPagamentoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblFormasPagamento", new tblFormasPagamento());
        return "formaspagamento";
    }

    @GetMapping
    public String listarFormasPagamentos(Model model) {
        model.addAttribute("listarFormasPagamentos", service.getActives());
        model.addAttribute("tblFormasPagamento", new tblFormasPagamento());
        return "formaspagamento";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblFormasPagamento table) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        table.setAtivo(true);

        if (table.getId() != null && table.getId() != 0){
            Optional<tblFormasPagamento> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.update(table);
        }
        else {
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(activeUserId);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.create(table);
        }

        return "redirect:/index?origem=formaspagamento";
    }

    @GetMapping("/novo")
    public String novoSituacaoAgendamento(Model model) {
        model.addAttribute("listarFormasPagamentos", service.getActives());
        model.addAttribute("tblFormasPagamento", new tblFormasPagamento());
        return "formaspagamento";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index?origem=formaspagamento";
    }
}