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
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblBloqueio", new tblBloqueio());
        return "bloqueio";
    }

    @GetMapping
    public String listarDependencias(Model model) {
        String userId = Cookies.getUserId();

        if (userId != null) {
            model.addAttribute("userId", userId);
            model.addAttribute("listarBloqueios", service.getActivesByUser(Integer.parseInt(userId)));
            model.addAttribute("listarHorarios", horarioService.getActiveByHorarioAsc());
            model.addAttribute("listarPeriodos", periodoService.getActives());
            model.addAttribute("listarDiasSemana", diasSemanaService.getActives());
            model.addAttribute("listarColaboradores", usuarioService.getAllByPerfil(5));
            model.addAttribute("tblBloqueio", new tblBloqueio());
        }
        return "bloqueio";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblBloqueio table, @RequestParam("userId") String userId, Model model) {
        tblPeriodo periodo = periodoService.getById(table.getPeriodo().getId()).orElseThrow();
        tblDiasSemana diasSemana = diasSemanaService.getById(table.getDiasSemana().getId()).orElseThrow();

        table.setAtivo(true);
        table.setPeriodo(periodo);
        table.setDiasSemana(diasSemana);

        if (table.getId() != null && table.getId() != 0){
            tblBloqueio data = service.getById(table.getId()).orElseThrow();

            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.getDataCriacao());
            table.setCriadoPor(data.getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(Integer.parseInt(userId));
            service.update(table);
        }
        else {
            table.setDataCriacao(LocalDateTime.now());
            table.setDataAlteracao(LocalDateTime.now());
            table.setCriadoPor(table.getCriadoPor());
            table.setAlteradoPor(Integer.parseInt(userId));
            service.create(table);
        }

        // Atualiza o modelo com os dados necessários para renderizar a view parcial
        model.addAttribute("userId", userId);
        model.addAttribute("listarBloqueios", service.getActivesByUser(Integer.parseInt(userId)));
        model.addAttribute("listarHorarios", horarioService.getActiveByHorarioAsc());
        model.addAttribute("listarPeriodos", periodoService.getActives());
        model.addAttribute("listarDiasSemana", diasSemanaService.getActives());
        model.addAttribute("listarColaboradores", usuarioService.getAllByPerfil(5));
        model.addAttribute("tblBloqueio", new tblBloqueio());

        return "redirect:/index";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }
}