package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.SituacaoAgendamentoService;
import br.com.augustocosta.acs.integration.entity.tblSituacaoAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/situacaoagendamento")
public class SituacaoAgendamentoController {

    @Autowired
    private SituacaoAgendamentoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblSituacaoAgendamento", new tblSituacaoAgendamento());
        return "situacaoagendamento";
    }

    @GetMapping
    public String listarSituacaoAgendamentos(Model model) {
        model.addAttribute("listarSituacaoAgendamentos", service.getActiveByNameAsc());
        model.addAttribute("tblSituacaoAgendamento", new tblSituacaoAgendamento());
        return "situacaoagendamento"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblSituacaoAgendamento table) {
        table.setAtivo(true);
        if (table.getId() != null && table.getId() != 0){
            Optional<tblSituacaoAgendamento> data = service.getById(table.getId());
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
    public String novoSituacaoAgendamento(Model model) {
        model.addAttribute("listarSituacaoAgendamentos", service.getActiveByNameAsc());
        model.addAttribute("tblSituacaoAgendamento", new tblSituacaoAgendamento());
        return "situacaoagendamento";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }
}