package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.business.service.SituacaoCompraService;
import br.com.augustocosta.acs.integration.entity.tblSituacaoCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/situacaocompra")
public class SituacaoCompraController {

    @Autowired
    private SituacaoCompraService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblSituacaoCompra", new tblSituacaoCompra());
        return "situacaocompra";
    }

    @GetMapping
    public String listarSituacaoCompras(Model model) {
        model.addAttribute("listarSituacaoCompras", service.getActiveByNameAsc());
        model.addAttribute("tblSituacaoCompra", new tblSituacaoCompra());
        return "situacaocompra"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblSituacaoCompra table) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;

        table.setAtivo(true);
        if (table.getId() != null && table.getId() != 0){
            Optional<tblSituacaoCompra> data = service.getById(table.getId());
            table.setAtivo(table.getAtivo());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.update(table.getId(), table);
        }
        else {
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(activeUserId);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(activeUserId);
            service.create(table);
        }
        return "redirect:/index?origem=situacaocompra";
   }

    @GetMapping("/novo")
    public String novoSituacaoAgendamento(Model model) {
        model.addAttribute("listarSituacaoAgendamentos", service.getActiveByNameAsc());
        model.addAttribute("tblSituacaoCompra", new tblSituacaoCompra());
        return "situacaocompra";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index?origem=situacaocompra";
    }
}