package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.UsuarioService;
import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import br.com.augustocosta.acs.integration.dto.dtoLogin;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Controller
@RequestMapping("/acesso")
public class AcessoController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginPage() {
        return "acesso";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblUsuario", new tblUsuario());
        return "acesso";
    }

    @GetMapping
    public String loginPage() {
        return "acesso";
    }

    @PostMapping("/verify")
    public String login(@ModelAttribute("loginDTO") dtoLogin loginDTO, HttpServletResponse response, Model model) {
        boolean isValidUser = usuarioService.validateLogin(loginDTO.getEmail(), loginDTO.getSenha());

        if (isValidUser) {
            tblUsuario usuario = usuarioService.getValidateUserByEmail(loginDTO.getEmail());
            createAndSetCookies(response, usuario);

            return "redirect:/index";
        } else {
            model.addAttribute("loginError", "Invalid email or password.");
            return "acesso";
        }
    }

    private void createAndSetCookies(HttpServletResponse response, tblUsuario usuario) {
        String sessionId = UUID.randomUUID().toString();
        Cookie sessionCookie = new Cookie("session_id", sessionId);
        Cookie userIdCookie = new Cookie("userId", String.valueOf(usuario.getId()));

        String perfilValue = String.valueOf(usuario.getPerfil().getId());
        String encodedPerfilValue = URLEncoder.encode(perfilValue, StandardCharsets.UTF_8);
        Cookie perfilCookie = new Cookie("perfil", encodedPerfilValue);

        userIdCookie.setHttpOnly(true);
        userIdCookie.setPath("/");

        perfilCookie.setHttpOnly(true);
        perfilCookie.setPath("/");

        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true);
        sessionCookie.setMaxAge(7 * 24 * 60 * 60);

        response.addCookie(userIdCookie);
        response.addCookie(perfilCookie);
        response.addCookie(sessionCookie);

        Cookies.addUserIdCookie(response, String.valueOf(usuario.getId()));
    }
}