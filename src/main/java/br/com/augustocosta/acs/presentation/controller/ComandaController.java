package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.dto.dtoComanda;
import br.com.augustocosta.acs.integration.dto.dtoProdutoVenda;
import br.com.augustocosta.acs.integration.entity.tblComanda;
import br.com.augustocosta.acs.integration.entity.tblComandaPagamento;
import br.com.augustocosta.acs.integration.entity.tblProduto;
import br.com.augustocosta.acs.integration.entity.tblServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/comanda")
public class ComandaController {

    @Autowired
    private ComandaService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ServicosAgendamentoService servicoAgendamentoService;

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ComandaPagamentoService comandaPagamentoService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dtoComanda", new dtoComanda());
        return "comanda";
    }

    @GetMapping
    public String listarComandas(Model model) {
        model.addAttribute("listarComandas", service.getAllByAtivo());
        model.addAttribute("listarClientes", usuarioService.getAllByPerfil(4));
        model.addAttribute("listarColaboradores", usuarioService.getAllByPerfil(5));
        model.addAttribute("listarServiçosAgendamento", servicoAgendamentoService.getServicoByAgendamentoId(0));
        model.addAttribute("listarProdutosAgendamento", agendamentoService.getProdutoByAgendamentoId(0));
        model.addAttribute("listarPagamentos", comandaPagamentoService.getByComanda(0));
        model.addAttribute("dtoComanda", new dtoComanda());
        return "comanda";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute dtoComanda dados) {
        tblComanda table = service.getTableById(dados.getComandaId());
        table.setSituacao(dados.getSituacao());
        table.setDataAlteracao(LocalDateTime.now());
        table.setAlteradoPor(1);
        service.update(table);
        return "redirect:/index";
    }

    @GetMapping("/novo")
    public String novoSituacaoAgendamento(Model model) {
        model.addAttribute("listarComandas", service.getActives());
        model.addAttribute("tblComanda", new tblComanda());
        return "comanda";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }

    @GetMapping("/servicos/{agendamentoId}")
    @ResponseBody
    public ResponseEntity<List<tblServico>> listarServicosPorAgendamento(@PathVariable Integer agendamentoId) {
        List<tblServico> servicos = servicoAgendamentoService.getServicoByAgendamentoId(agendamentoId);
        return ResponseEntity.ok(servicos);
    }

    @GetMapping("/produtos/{agendamentoId}")
    @ResponseBody
    public ResponseEntity<List<dtoProdutoVenda>> listarProdutosPorCompra(@PathVariable Integer agendamentoId) {
        List<dtoProdutoVenda> produtos = agendamentoService.getProdutoByAgendamentoId(agendamentoId);
        return ResponseEntity.ok(produtos);
    }
}