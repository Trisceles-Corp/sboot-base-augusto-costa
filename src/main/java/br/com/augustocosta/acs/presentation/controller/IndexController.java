package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.UsuarioService;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblUsuario", new tblUsuario());
        return "index";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("tblUsuario", new tblUsuario());
        return "index";
    }

}
