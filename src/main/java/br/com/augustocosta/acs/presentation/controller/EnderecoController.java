package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.EnderecoService;
import br.com.augustocosta.acs.integration.entity.tblEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblEndereco", new tblEndereco());
        return "endereco";
    }

    @GetMapping
    public String listarEnderecos(Model model) {
        model.addAttribute("listaEnderecos", service.getActiveByNameAsc());
        model.addAttribute("tblEndereco", new tblEndereco());
        return "endereco"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblEndereco table) {
        if (table.getId() != null && table.getId() != 0){
            Optional<tblEndereco> data = service.getById(table.getId());
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

        return "redirect:/endereco";
    }
}