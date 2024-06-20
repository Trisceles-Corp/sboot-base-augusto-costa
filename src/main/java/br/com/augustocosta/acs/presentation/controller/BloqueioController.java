package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.integration.entity.tblBloqueio;
import br.com.augustocosta.acs.integration.entity.tblPeriodo;
import br.com.augustocosta.acs.integration.entity.tblDiasSemana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/bloqueio")
public class BloqueioController {

    @Autowired
    private BloqueioService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PeriodoService periodoService;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private DiasSemanaService diasSemanaService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model, HttpServletRequest request) {
        Cookies.setUserId(request);
        model.addAttribute("tblBloqueio", new tblBloqueio());
        return "bloqueio";
    }

    @GetMapping
    public String listarDependencias(Model model, HttpServletRequest request) {
        Cookies.setUserId(request);
        String userId = Cookies.getUserId();
        if(userId == null){ userId = "1"; }
        int activeUserId = Integer.parseInt(userId);

        model.addAttribute("userId", userId);
        model.addAttribute("listarBloqueios", service.getActivesByUser(activeUserId));
        model.addAttribute("listarHorarios", horarioService.getActiveByHorarioAsc());
        model.addAttribute("listarPeriodos", periodoService.getActives());
        model.addAttribute("listarDiasSemana", diasSemanaService.getActives());
        model.addAttribute("listarColaboradores", usuarioService.getAllByPerfil(5));
        model.addAttribute("tblBloqueio", new tblBloqueio());

        return "bloqueio";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblBloqueio table, HttpServletRequest request, Model model) {
        Cookies.setUserId(request);
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie);
        tblPeriodo periodo = periodoService.getById(table.getPeriodo().getId()).orElseThrow();
        tblDiasSemana diasSemana = diasSemanaService.getById(table.getDiasSemana().getId()).orElseThrow();

        table.setAtivo(true);
        table.setPeriodo(periodo);
        table.setDiasSemana(diasSemana);

        if (table.getId() != null && table.getId() != 0){
            service.update(table, activeUserId);
        }
        else {
            service.create(table, activeUserId);
        }

        model.addAttribute("userId", userCookie);
        model.addAttribute("listarBloqueios", service.getActivesByUser(activeUserId));
        model.addAttribute("listarHorarios", horarioService.getActiveByHorarioAsc());
        model.addAttribute("listarPeriodos", periodoService.getActives());
        model.addAttribute("listarDiasSemana", diasSemanaService.getActives());
        model.addAttribute("listarColaboradores", usuarioService.getAllByPerfil(5));
        model.addAttribute("tblBloqueio", new tblBloqueio());

        return "redirect:/index?origem=gridAgendamento";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, HttpServletRequest request) {
        Cookies.setUserId(request);
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie);
        service.delete(id, activeUserId);
        return "redirect:/index?origem=bloqueio";
    }
}