package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.EnderecoService;
import br.com.augustocosta.acs.business.service.UsuarioService;
import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.integration.dto.dtoUsuario;
import br.com.augustocosta.acs.integration.entity.tblEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dtoUsuario", new dtoUsuario());
        return "usuario";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaUsuarios", service.getActiveByNameAsc());
        model.addAttribute("listaPerfil", service.getAllActivesByPerfil());
        model.addAttribute("listaCargos", service.getAllActivesByCargo());
        model.addAttribute("dtoUsuario", new dtoUsuario());
        return "usuario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute dtoUsuario dados) {
        dados.setDdiCelular(55);
        dados.setDdiTelefone(55);
        if (dados.getUsuarioId() != null && dados.getUsuarioId() != 0){
            tblEndereco saveEndereco = enderecoService.updateDto(dados);
            service.updateDto(saveEndereco, dados);
        }
        else {
            service.createDto(enderecoService.createDto(dados), dados);
        }
        return "redirect:/index?origem=usuario";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index?origem=usuario";
    }
}