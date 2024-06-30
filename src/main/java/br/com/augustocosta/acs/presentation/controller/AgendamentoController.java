package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.*;
import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.integration.dto.dtoAgendamento;
import br.com.augustocosta.acs.integration.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
    public String listarDependencias(@RequestParam(value = "inputData", required = false) String dataAgendaStr, Model model) {
        Date dataAgenda;
        if (dataAgendaStr == null || dataAgendaStr.isEmpty()) {
            dataAgenda = new Date();
        } else {
            LocalDate inputDate = LocalDate.parse(dataAgendaStr);
            dataAgenda = Date.from(inputDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        model.addAttribute("listarLocaisEstoque", localService.getActives());
        model.addAttribute("listarServiços", servicoService.getActives());
        model.addAttribute("listarHorarios", horarioService.getActiveByHorarioColaborador(dataAgenda, 0));
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

        if(dados.getServico() == null || dados.getServico().size() == 0){
            return "redirect:/index?origem=agendamento";
        }

        tblAgendamento table = dados.getAgendamento();
        table.setAtivo(true);

        if (table.getId() != null && table.getId() != 0){
            service.update(dados, activeUserId);
        }
        else {
            service.create(dados, activeUserId);
        }

        return "redirect:/index?origem=gridagendamento";
    }

    @GetMapping("/{id}")
    public String mostrarAgendamento(@PathVariable("id") Integer agendamentoId, Model model) {
        tblAgendamento agendamento = service.getById(agendamentoId).orElseThrow();
        dtoAgendamento dto = new dtoAgendamento();
        dto.setAgendamento(agendamento);
        model.addAttribute("dtoAgendamento", dto);

        // Adicione os outros atributos necessários para a página de agendamento
        model.addAttribute("listarLocaisEstoque", localService.getActives());
        model.addAttribute("listarServiços", servicoService.getActives());
        model.addAttribute("listarHorarios", horarioService.getActiveByHorarioAsc());
        model.addAttribute("listarServiçosAgendamento", servicoAgendamentoService.getServicoByAgendamentoId(agendamentoId));
        model.addAttribute("listarProdutosAgendamento", service.getProdutoByAgendamentoId(agendamentoId));
        model.addAttribute("listarProdutos", produtoService.getActives());
        model.addAttribute("listarClientes", usuarioService.getAllByPerfil(4));
        model.addAttribute("listarColaboradores", usuarioService.getAllByPerfil(5));
        model.addAttribute("listarSituacao", situacaoAgendamentoService.getActives());

        return "agendamento";
    }

    @GetMapping("/horarios/{dataAgenda}/{colaboradorId}")
    @ResponseBody
    public List<tblHorario> getHorariosDisponiveis(@PathVariable("dataAgenda") String dataAgendaStr, @PathVariable("colaboradorId") Integer colaboradorId) {
        Date dataAgenda = Date.from(LocalDate.parse(dataAgendaStr).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return horarioService.getActiveByHorarioColaborador(dataAgenda, colaboradorId);
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
        return "redirect:/index?origem=agendamento";
    }
}