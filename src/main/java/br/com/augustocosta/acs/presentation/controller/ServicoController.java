package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.ServicoService;
import br.com.augustocosta.acs.integration.entity.tblServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblServico", new tblServico());
        return "servico";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaServicos", service.getActiveByNameAsc());
        model.addAttribute("tblServico", new tblServico());
        return "servico"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblServico table) {
        table.setAtivo(true);
        if (table.getId() != null && table.getId() != 0){
            Optional<tblServico> data = service.getById(table.getId());
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

        return "redirect:/index?origem=servico";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listaServicos", service.getActiveByNameAsc());
        model.addAttribute("tblServico", new tblServico());
        return "servico";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index?origem=servico";
    }
}