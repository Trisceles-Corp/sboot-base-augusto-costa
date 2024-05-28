package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.business.util.Cookies;
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
    private HorarioService horarioService;

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
        model.addAttribute("listarHorarios", horarioService.getActiveByHorarioAsc());
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
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        Integer activeUserId = Integer.parseInt(userCookie) ;

        tblAgendamento table = dados.getAgendamento();
        table.setAtivo(true);

        if (table.getId() != null && table.getId() != 0){
            Optional<tblAgendamento> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.update(table);
        }
        else {
            service.create(dados, activeUserId);
        }

        return "redirect:/index";
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
        tblProduto produto = produtoService.getById(produtoId).orElseThrow();
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        Integer activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index";
    }
}