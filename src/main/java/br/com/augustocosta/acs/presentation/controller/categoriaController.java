package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.CategoriaService;
import br.com.augustocosta.acs.integration.entity.tblCategoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/categoria")
public class categoriaController {

    private final CategoriaService service;

    public categoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblCategoria", new tblCategoria());
        return "categoria";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaCategorias", service.getActivesByName());
        model.addAttribute("tblCategoria", new tblCategoria());
        return "categoria";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblCategoria categoria) {
        if (categoria.getId() != null && categoria.getId() != 0){
            Optional<tblCategoria> data = service.getById(categoria.getId());
            categoria.setAtivo(categoria.getAtivo());
            categoria.setDataCriacao(data.orElseThrow().getDataCriacao());
            categoria.setCriadoPor(data.get().getCriadoPor());
            categoria.setDataAlteracao(LocalDateTime.now());
            categoria.setAlteradoPor(1);
            service.update(categoria.getId(), categoria);
        }
        else {
            categoria.setAtivo(true);
            categoria.setDataCriacao(LocalDateTime.now());
            categoria.setCriadoPor(1);
            categoria.setDataAlteracao(LocalDateTime.now());
            categoria.setAlteradoPor(1);
            service.create(categoria);
        }

        return "redirect:/categoria";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("tblCategoria", new tblCategoria());
        return "redirect:/categoria";
    }

    // Implemente os métodos para visualizar, editar e excluir conforme necessário
}