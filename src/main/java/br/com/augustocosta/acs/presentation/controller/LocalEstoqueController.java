package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.LocalEstoqueService;
import br.com.augustocosta.acs.integration.entity.tblLocalEstoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/localestoque")
public class LocalEstoqueController {

    @Autowired
    private LocalEstoqueService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblLocalEstoque", new tblLocalEstoque());
        return "localestoque";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listalocais", service.getActiveByNameAsc());
        model.addAttribute("tblLocalEstoque", new tblLocalEstoque());
        return "localestoque"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblLocalEstoque table) {
        table.setAtivo(true);
        if (table.getId() != null && table.getId() != 0){
            Optional<tblLocalEstoque> data = service.getById(table.getId());
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
        return "redirect:/index?origem=localestoque";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listalocais", service.getActiveByNameAsc());
        model.addAttribute("tblLocalEstoque", new tblLocalEstoque());
        return "localestoque";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index?origem=localestoque";
    }
}