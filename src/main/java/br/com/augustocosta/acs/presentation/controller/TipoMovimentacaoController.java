package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.business.service.TipoMovimentacaoService;
import br.com.augustocosta.acs.integration.entity.tblTipoMovimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/tipomovimentacao")
public class TipoMovimentacaoController {

    @Autowired
    private TipoMovimentacaoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblTipoMovimentacao", new tblTipoMovimentacao());
        return "tipomovimentacao";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaTipoMovimentacao", service.getActiveByNameAsc());
        model.addAttribute("tblTipoMovimentacao", new tblTipoMovimentacao());
        return "tipomovimentacao"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblTipoMovimentacao table) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        if (table.getId() != null && table.getId() != 0){
            Optional<tblTipoMovimentacao> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.update(table.getId(), table);
        }
        else {
            table.setAtivo(true);
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(activeUserId);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.create(table);
        }
        return "redirect:/index?origem=tipomovimentacao";
   }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listaTipoMovimentacao", service.getActiveByNameAsc());
        model.addAttribute("tblTipoMovimentacao", new tblTipoMovimentacao());
        return "tipomovimentacao";
    }
}