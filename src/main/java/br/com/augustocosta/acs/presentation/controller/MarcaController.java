package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.MarcaService;
import br.com.augustocosta.acs.integration.entity.tblMarca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblMarca", new tblMarca());
        return "marca";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaMarcas", service.getActiveByNameAsc());
        model.addAttribute("tblMarca", new tblMarca());
        return "marca"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblMarca table) {
        if (table.getId() != null && table.getId() != 0){
            Optional<tblMarca> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.update(table.getId(), table);
        }
        else {
            table.setAtivo(true);
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(1);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.create(table);
        }

        return "redirect:/marca";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listaMarcas", service.getActiveByNameAsc());
        model.addAttribute("tblMarca", new tblMarca());
        return "marca";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}