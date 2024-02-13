package br.com.augustocosta.acs.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Redireciona para o controller categoriaController ao acessar a raiz
        return "redirect:/categoria";
    }
}
