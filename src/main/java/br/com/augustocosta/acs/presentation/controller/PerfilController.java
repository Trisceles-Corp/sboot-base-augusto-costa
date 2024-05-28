package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.business.service.PerfilService;
import br.com.augustocosta.acs.integration.entity.tblPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblPerfil", new tblPerfil());
        return "perfil";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaPerfil", service.getActiveByNameAsc());
        model.addAttribute("listaTiposPerfil", service.getAllTipoPerfil());
        model.addAttribute("tblPerfil", new tblPerfil());
        return "perfil";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblPerfil table) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;

        table.setAtivo(true);
        if (table.getId() != null && table.getId() != 0){
            Optional<tblPerfil> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.update(table.getId(), table);
        }
        else {
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(activeUserId);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.create(table);
        }
        return "redirect:/index?origem=perfil";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listaTipos", service.getActiveByNameAsc());
        model.addAttribute("tblPerfil", new tblPerfil());
        return "perfil";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index?origem=perfil";
    }
}