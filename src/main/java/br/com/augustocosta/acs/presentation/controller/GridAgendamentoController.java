package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.GridAgendamentoService;
import br.com.augustocosta.acs.integration.dto.dtoGridAgendamento;
import br.com.augustocosta.acs.integration.entity.tblGridAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/gridagendamento")
public class GridAgendamentoController {

    @Autowired
    private GridAgendamentoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblGridAgendamento", new tblGridAgendamento());
        return "gridagendamento";
    }

    @GetMapping
    public String listarDependencias(@RequestParam(value = "dataAgenda", required = false) String dataAgendaStr, Model model) {
        LocalDate dataAgenda;
        if (dataAgendaStr == null || dataAgendaStr.isEmpty()) {
            dataAgenda = LocalDate.now();
        } else {
            dataAgenda = LocalDate.parse(dataAgendaStr);
        }
        List<dtoGridAgendamento> agendamentos = service.getByGridAgendamento(dataAgenda);
        if (agendamentos.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = dataAgenda.format(formatter);
            model.addAttribute("mensagemErro", "Não existem agendamentos para a data " + dataFormatada + ".");
        }
        model.addAttribute("listarAgendamentos", service.getByGridAgendamento(dataAgenda));
        model.addAttribute("tblGridAgendamento", new tblGridAgendamento());
        return "gridagendamento";
    }

    @GetMapping("/{data}")
    @ResponseBody
    public ResponseEntity<List<dtoGridAgendamento>> listarAgendamentosPorData(@PathVariable LocalDate data, Model model) throws SQLException {
        model.addAttribute("listarAgendamentos", service.getByGridAgendamento(data));
        List<dtoGridAgendamento> agendamentos = service.getByGridAgendamento(data);
        return ResponseEntity.ok(agendamentos);
    }
}
