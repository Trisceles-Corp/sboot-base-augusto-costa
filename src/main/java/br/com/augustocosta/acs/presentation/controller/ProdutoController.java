package br.com.augustocosta.acs.presentation.controller;

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
        table.setAtivo(true);

        if (table.getId() != null && table.getId() != 0){
            Optional<tblProduto> data = service.getById(table.getId());
            table.setDataCriacao(data.orElseThrow().getDataCriacao());
            table.setCriadoPor(data.get().getCriadoPor());
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.update(table.getId(), table);
        }
        else {
            table.setDataCriacao(LocalDateTime.now());
            table.setCriadoPor(1);
            table.setDataAlteracao(LocalDateTime.now());
            table.setAlteradoPor(1);
            service.create(table);
        }
        return "redirect:/index";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id, 1);
        return "redirect:/index";
    }
}