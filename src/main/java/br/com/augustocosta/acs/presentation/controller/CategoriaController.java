package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.CategoriaService;
import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.integration.entity.tblCategoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblCategoria", new tblCategoria());
        return "categoria";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaCategorias", service.getActiveByNameAsc());
        model.addAttribute("tblCategoria", new tblCategoria());
        return "categoria";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblCategoria categoria) {
        String userId = Cookies.getUserId();
        categoria.setAtivo(true);
        if (categoria.getId() != null && categoria.getId() != 0){
            Optional<tblCategoria> data = service.getById(categoria.getId());
            categoria.setAtivo(categoria.getAtivo());
            categoria.setDataCriacao(data.orElseThrow().getDataCriacao());
            categoria.setCriadoPor(data.get().getCriadoPor());
            categoria.setDataAlteracao(LocalDateTime.now());
            categoria.setAlteradoPor(Integer.parseInt(userId));
            service.update(categoria.getId(), categoria);
        }
        else {
            categoria.setDataCriacao(LocalDateTime.now());
            categoria.setCriadoPor(Integer.parseInt(userId));
            categoria.setDataAlteracao(LocalDateTime.now());
            categoria.setAlteradoPor(Integer.parseInt(userId));
            service.create(categoria);
        }
        return "redirect:/index?origem=categoria";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userId = Cookies.getUserId();
        service.delete(id, Integer.parseInt(userId));
        return "redirect:/index?origem=categoria";
    }
}