package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.UsuarioService;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import br.com.augustocosta.acs.integration.dto.dtoLogin;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
        return "login2";
    }

    @GetMapping
    public String loginPage() {
        return "login2";
    }

    @PostMapping("/verify")
    public String login(dtoLogin loginDTO, HttpServletResponse response, Model model) {
        boolean isValidUser = usuarioService.validateLogin(loginDTO.getEmail(), loginDTO.getSenha());

        if (isValidUser) {
            tblUsuario usuario = usuarioService.getValidateUserByEmail(loginDTO.getEmail());
            Cookie sessionCookie = new Cookie("session_id", "identificador_unico");
            Cookie cookie = new Cookie("userId", String.valueOf(usuario.getId()));
            cookie.setHttpOnly(true); // Importante para prevenir XSS
            cookie.setPath("/");

            sessionCookie.setHttpOnly(true);
            sessionCookie.setSecure(true); // Use apenas em conexões HTTPS
            sessionCookie.setMaxAge(7 * 24 * 60 * 60); // Expira em 7 dias

            response.addCookie(cookie);
            response.addCookie(sessionCookie);

            return "redirect:/index";
        } else {
            model.addAttribute("loginError", "Invalid email or password.");
            return "login2";
        }
    }
}
