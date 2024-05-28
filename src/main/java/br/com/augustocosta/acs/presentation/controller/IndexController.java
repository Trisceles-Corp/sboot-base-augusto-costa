package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.UsuarioService;
import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.integration.entity.tblUsuario;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public String agendamentoPage(HttpServletRequest request, Model model) {
<<<<<<< HEAD
        Cookies.setUserId(request);

        String userId = Cookies.getUserId();

=======
        // Acessar o cookie
        Cookie[] cookies = request.getCookies();
        String userId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    userId = cookie.getValue();
                    break;
                }
            }
        }
>>>>>>> main
        if (userId != null) {
            tblUsuario usuario = usuarioService.getUsuarioById(Integer.parseInt(userId));
            model.addAttribute("usuario", usuario);
        }
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
<<<<<<< HEAD
        request.getSession().invalidate();
=======
        // Invalidar a sessão
        request.getSession().invalidate();

        // Limpar os cookies
>>>>>>> main
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
<<<<<<< HEAD
        return "redirect:/acesso";
    }
=======

        return "redirect:/acesso";
    }

>>>>>>> main
}
