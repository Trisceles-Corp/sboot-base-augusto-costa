package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.dto.dtoComanda;
import br.com.augustocosta.acs.integration.dto.dtoProdutoVenda;
import br.com.augustocosta.acs.integration.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comanda")
public class ComandaController {

    @Autowired
    private ComandaService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CaixaService caixaService;

    @Autowired
    private ServicosAgendamentoService servicoAgendamentoService;

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ComandaPagamentoService comandaPagamentoService;

    @Autowired
    private FormasPagamentoService formasPagamentoService;

    @Autowired
    private BandeirasService bandeirasService;

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
        model.addAttribute("listarFormaPagamentos", formasPagamentoService.getActiveByNameAsc());
        model.addAttribute("listarCaixas", caixaService.getActiveByNameAsc());
        model.addAttribute("listarBandeiras", bandeirasService.getActiveByNameAsc());
        model.addAttribute("dtoComanda", new dtoComanda());
        return "comanda";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute dtoComanda dados) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.update(dados, activeUserId);
        return "redirect:/index?origem=comanda";
    }

    @GetMapping("/novo")
    public String novoSituacaoAgendamento(Model model) {
        model.addAttribute("listarComandas", service.getActives());
        model.addAttribute("tblComanda", new tblComanda());
        return "comanda";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index?origem=comanda";
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

    @GetMapping("/listaFormasPagamento/{formasPagamentoId}")
    @ResponseBody
    public ResponseEntity<tblFormasPagamento> listaPagamento(@PathVariable("formasPagamentoId") Integer formasPagamentoId) {
        tblFormasPagamento tblFormasPagamento = formasPagamentoService.getByFormaPagamentoId(formasPagamentoId);
        return ResponseEntity.ok(tblFormasPagamento);
    }

    @GetMapping("/listaBandeiras/{bandeiraId}")
    @ResponseBody
    public ResponseEntity<tblBandeiras> listaBandeiras(@PathVariable("bandeiraId") Integer bandeiraId) {
        tblBandeiras bandeiras = bandeirasService.getByBandeiraId(bandeiraId);
        return ResponseEntity.ok(bandeiras);
    }
}