package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.dto.dtoComanda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
@RequestMapping("/comissoes")
public class ComissoesController {

    @Autowired
    private ComandaService service;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dtoComanda", new dtoComanda());
        return "comissoes";
    }

    @GetMapping
    public String listarDependencias(Model model) {
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        model.addAttribute("listarComissoes", service.getAllComissoesByAtivo(0, firstDayOfMonth, lastDayOfMonth));
        model.addAttribute("listarColaboradores", usuarioService.getAllByPerfil(5));
        model.addAttribute("dtoComanda", new dtoComanda());
        return "comissoes";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute dtoComanda dados) {

        return "redirect:/index?origem=comissoes";
    }

    @GetMapping("/novo")
    public String novoSituacaoAgendamento(Model model) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        model.addAttribute("listarComissoes", service.getAllComissoesByAtivo(activeUserId, firstDayOfMonth, lastDayOfMonth));
        model.addAttribute("listarColaboradores", usuarioService.getAllByPerfil(5));
        model.addAttribute("dtoComanda", new dtoComanda());
        return "comissoes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index?origem=comissoes";
    }

    @GetMapping("/listarComissoes/{colaboradorId}/{firstDay}/{lastDay}")
    @ResponseBody
    public ResponseEntity<List<dtoComanda>> listarComissoes(
            @PathVariable Integer colaboradorId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firstDay,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastDay) {
        List<dtoComanda> comissoes = service.getAllComissoesByAtivo(colaboradorId, firstDay, lastDay);
        return ResponseEntity.ok(comissoes);
    }
}