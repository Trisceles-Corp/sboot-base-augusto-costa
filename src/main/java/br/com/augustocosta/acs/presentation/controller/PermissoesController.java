package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.business.service.PermissoesService;
import br.com.augustocosta.acs.integration.entity.tblPermissoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/permissoes")
public class PermissoesController {

    @Autowired
    private PermissoesService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblPermissoes", new tblPermissoes());
        return "permissoes";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaPermissoes", service.getActiveByNameAsc());
        model.addAttribute("tblPermissoes", new tblPermissoes());
        return "permissoes"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblPermissoes table) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        if (table.getId() != null && table.getId() != 0){
            Optional<tblPermissoes> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.update(table.getId(), table);
        }
        else {
            table.setAtivo(true);
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(activeUserId);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.create(table);
        }
        return "redirect:/index?origem=permissoes";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listaPermissoes", service.getActiveByNameAsc());
        model.addAttribute("tblPermissoes", new tblPermissoes());
        return "permissoes";
    }
}