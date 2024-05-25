package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.TipoPerfilService;
import br.com.augustocosta.acs.integration.entity.tblTipoPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/tipoperfil")
public class TipoPerfilController {

    @Autowired
    private TipoPerfilService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblTipoPerfil", new tblTipoPerfil());
        return "tipoperfil";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaTipos", service.getActiveByNameAsc());
        model.addAttribute("tblTipoPerfil", new tblTipoPerfil());
        return "tipoperfil";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblTipoPerfil table) {
        if (table.getId() != null && table.getId() != 0){
            Optional<tblTipoPerfil> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.update(table.getId(), table);
        }
        else {
            table.setAtivo(true);
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(1);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.create(table);
        }

        return "redirect:/index";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listaTipos", service.getActiveByNameAsc());
         model.addAttribute("tblTipoPerfil", new tblTipoPerfil());
        return "tipoperfil";
    }
}