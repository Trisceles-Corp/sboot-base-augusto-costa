package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.UsuarioService;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import br.com.augustocosta.acs.integration.dto.dtoLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login2")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblUsuario", new tblUsuario());
        return "login";
    }

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping("/verify")
    public String login(dtoLogin loginDTO, Model model) {
        boolean isValidUser = usuarioService.validateLogin(loginDTO.getEmail(), loginDTO.getSenha());

        if (isValidUser) {
            return "redirect:/index";
        } else {
            model.addAttribute("loginError", "Invalid email or password.");
            return "login";
        }
    }
}