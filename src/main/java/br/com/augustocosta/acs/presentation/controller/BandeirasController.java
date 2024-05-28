package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.integration.entity.tblBandeiras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/bandeiras")
public class BandeirasController {

    @Autowired
    private BandeirasService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblBandeiras", new tblBandeiras());
        return "bandeiras";
    }

    @GetMapping
    public String listarBandeiras(Model model) {
        model.addAttribute("listarBandeiras", service.getActives());
        model.addAttribute("tblBandeiras", new tblBandeiras());
        return "bandeiras";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblBandeiras table) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        table.setAtivo(true);

        if (table.getId() != null && table.getId() != 0){
            Optional<tblBandeiras> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.update(table);
        }
        else {
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(activeUserId);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.create(table);
        }

        return "redirect:/index?origem=bandeiras";
    }

    @GetMapping("/novo")
    public String novoSituacaoAgendamento(Model model) {
        model.addAttribute("listarBandeiras", service.getActives());
        model.addAttribute("tblBandeiras", new tblBandeiras());
        return "bandeiras";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index?origem=bandeiras";
    }
}