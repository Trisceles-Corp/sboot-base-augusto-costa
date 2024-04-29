package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.integration.dto.dtoAgendamento;
import br.com.augustocosta.acs.integration.entity.tblAgendamento;
import br.com.augustocosta.acs.integration.entity.tblProduto;
import br.com.augustocosta.acs.integration.entity.tblServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @Autowired
    private LocalEstoqueService localService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SituacaoAgendamentoService situacaoAgendamentoService;

    @Autowired
    private ServicosAgendamentoService servicoAgendamentoService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dtoAgendamento", new dtoAgendamento());
            return "agendamento";
    }

    @GetMapping
    public String listarDependencias(Model model) {
        model.addAttribute("listarLocaisEstoque", localService.getActives());
        model.addAttribute("listarServiços", servicoService.getActives());
        model.addAttribute("listarServiçosAgendamento", servicoAgendamentoService.getServicoByAgendamentoId(0));
        model.addAttribute("listarProdutosAgendamento", service.getProdutoByAgendamentoId(0));
        model.addAttribute("listarProdutos", produtoService.getActives());
        model.addAttribute("listarClientes", usuarioService.getAllByPerfil(4));
        model.addAttribute("listarColaboradores", usuarioService.getAllByPerfil(5));
        model.addAttribute("listarSituacao", situacaoAgendamentoService.getActives());
        model.addAttribute("dtoAgendamento", new dtoAgendamento());
        return "agendamento";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute dtoAgendamento dados) {
        tblAgendamento table = dados.getAgendamento();
        table.setAtivo(true);

        if (table.getId() != null && table.getId() != 0){
            Optional<tblAgendamento> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.update(table);
        }
        else {
            service.create(dados);
        }

        return "redirect:/index";
    }

    @GetMapping("/novo")
    public String novoSituacaoAgendamento(Model model) {
        model.addAttribute("listarLocaisEstoque", localService.getActives());
        model.addAttribute("listarServiços", servicoService.getActives());
        model.addAttribute("listarProdutos", produtoService.getActives());
        model.addAttribute("dtoAgendamento", new dtoAgendamento());
        return "agendamento";
    }

    @GetMapping("/listaServicoAgendamento/{servicoId}")
    @ResponseBody
    public ResponseEntity<tblServico> listaServicoAgendamento(@PathVariable("servicoId") Integer servicoId) {
        tblServico servico = servicoService.getByServiceId(servicoId);
        return ResponseEntity.ok(servico);
    }

    @GetMapping("/listaProdutoAgendamento/{produtoId}")
    @ResponseBody
    public ResponseEntity<tblProduto> listaProdutoAgendamento(@PathVariable("produtoId") Integer produtoId) {
        tblProduto produto = produtoService.getByProdutoId(produtoId);
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }
}