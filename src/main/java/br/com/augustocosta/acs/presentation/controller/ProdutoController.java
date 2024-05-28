package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.business.service.ProdutoService;
import br.com.augustocosta.acs.integration.entity.tblProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblProduto", new tblProduto());
        return "produto";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaProdutos", service.getActiveByNameAsc());
        model.addAttribute("listaMarcas", service.getActiveMarcaByNameAsc());
        model.addAttribute("listaCategorias", service.getAllActivesCategories());
        model.addAttribute("listaLinhas", service.getActiveLinhaByNameAsc());
        model.addAttribute("listaCaracteristicas", service.getActiveCaracteristicaByNameAsc());
        model.addAttribute("tblProduto", new tblProduto());
        return "produto";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblProduto table) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        table.setAtivo(true);

        if (table.getId() != null && table.getId() != 0){
            Optional<tblProduto> data = service.getById(table.getId());
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
        return "redirect:/index?origem=produto";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        service.delete(id, activeUserId);
        return "redirect:/index?origem=produto";
    }
}