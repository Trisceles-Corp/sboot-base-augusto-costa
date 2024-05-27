package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.entity.tblCaixaMovimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
@RequestMapping("/caixamovimentacao")
public class CaixaMovimentacaoController {

    @Autowired
    private CaixaMovimentacaoService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CaixaService caixaService;

    @Autowired
    private FormasPagamentoService formasPagamentoService;

    @Autowired
    private TipoMovimentacaoService tipoMovimentacaoService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblCaixaMovimentacao", new tblCaixaMovimentacao());
        return "caixamovimentacao";
    }

    @GetMapping
    public String listarDependencias(Model model) {
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        model.addAttribute("listarMovimentacoes", service.getFilterByDataCriacaoDesc(firstDayOfMonth, lastDayOfMonth));
        model.addAttribute("listarCaixas", caixaService.getActiveByNameAsc());
        model.addAttribute("listarColaboradores", usuarioService.getAllByPerfil(5));
        model.addAttribute("listarFormasPagamento", formasPagamentoService.getActiveByNameAsc());
        model.addAttribute("listarTipoMovimentacao", tipoMovimentacaoService.getActiveByNameAsc());
        model.addAttribute("tblCaixaMovimentacao", new tblCaixaMovimentacao());
        return "caixamovimentacao";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblCaixaMovimentacao dados) {
        if (dados.getId() != null && dados.getId() != 0){
            service.update(dados);
        }
        else {
            service.create(dados);
        }
        return "redirect:/index?origem=caixamovimentacao";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index?origem=caixamovimentacao";
    }

    @GetMapping("/listarMovimentacoes/{firstDay}/{lastDay}")
    @ResponseBody
    public ResponseEntity<List<tblCaixaMovimentacao>> listarMovimentacoes(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firstDay,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastDay) {
        List<tblCaixaMovimentacao> movimentacoes = service.getFilterByDataCriacaoDesc(firstDay, lastDay);
        return ResponseEntity.ok(movimentacoes);
    }
}