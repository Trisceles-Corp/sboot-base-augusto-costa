package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.UsuarioService;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblUsuario", new tblUsuario());
        return "cliente";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaClientes", service.getActiveByPerfilAsc(4));
        model.addAttribute("listaClientesPorCpf", service.getActiveByPerfilAsc(4));
        model.addAttribute("listaClientesPorEmail", service.getActiveByPerfilAsc(4));
        model.addAttribute("listaClientesPorNome", service.getActiveByPerfilAsc(4));
        model.addAttribute("listaClientesPorSobrenome", service.getActiveByPerfilAsc(4));
        model.addAttribute("listaPerfil", service.getAllActivesByPerfil());
        model.addAttribute("listCargos", service.getAllActivesByCargo());
        model.addAttribute("tblUsuario", new tblUsuario());
        return "cliente";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblUsuario table) {
        if (table.getId() != null && table.getId() != 0){
            Optional<tblUsuario> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.update(table.getId(), table);
        }
        else {
            table.setCriadoPor(1);
            table.setAlteradoPor(1);
            service.create(table);
        }

        return "redirect:/cliente";
    }
}