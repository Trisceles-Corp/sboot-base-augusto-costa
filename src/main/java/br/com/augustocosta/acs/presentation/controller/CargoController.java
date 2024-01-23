package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.CargoService;
import br.com.augustocosta.acs.integration.entity.tblCargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cargo", new tblCargo());
        return "cargo";
    }

    @GetMapping
    public String listarCargos(Model model) {
        model.addAttribute("cargos", cargoService.listarTodosCargos());
        model.addAttribute("cargo", new tblCargo());
        return "cargo"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvarCargo(@ModelAttribute tblCargo cargo) {
        cargoService.salvarCargo(cargo);
        return "redirect:/cargos";
    }

    @GetMapping("/novo")
    public String novoCargo(Model model) {
        model.addAttribute("cargo", new tblCargo());
        return "cargo";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}