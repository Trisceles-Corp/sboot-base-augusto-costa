package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.UsuarioService;
import br.com.augustocosta.acs.integration.dto.dtoLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(dtoLogin loginDTO, Model model) {
        boolean isValidUser = usuarioService.validateLogin(loginDTO.getEmail(), loginDTO.getSenha());

        if (isValidUser) {
            // Redirect to a secured page or dashboard
            return "redirect:/categoria";
        } else {
            model.addAttribute("loginError", "Invalid email or password.");
            return "login2"; // Assuming 'login2.jsp' is your login page
        }
    }
}