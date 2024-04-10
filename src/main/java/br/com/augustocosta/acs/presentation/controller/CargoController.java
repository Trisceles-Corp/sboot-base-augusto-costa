package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.CargoService;
import br.com.augustocosta.acs.integration.entity.tblCargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/cargo")
public class CargoController {

    @Autowired
    private CargoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblCargo", new tblCargo());
        return "cargo";
    }

    @GetMapping
    public String listarCargos(Model model) {
        model.addAttribute("listaCargos", service.getActiveByNameAsc());
        model.addAttribute("tblCargo", new tblCargo());
        return "cargo"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblCargo table) {
        table.setAtivo(true);
        if (table.getId() != null && table.getId() != 0){
            Optional<tblCargo> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.update(table.getId(), table);
        }
        else {
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(1);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.create(table);
        }

        return "redirect:/index";
    }

    @GetMapping("/novo")
    public String novoCargo(Model model) {
        model.addAttribute("listaCargos", service.getActiveByNameAsc());
        model.addAttribute("tblCargo", new tblCargo());
        return "cargo";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }
}