package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.CaracteristicaService;
import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.integration.entity.tblCaracteristica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;


@Controller
@RequestMapping("/caracteristica")
public class CaracteristicaController {

    @Autowired
    private CaracteristicaService service;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tblCaracteristica", new tblCaracteristica());
        return "caracteristica";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaCaracteristica", service.getActiveByNameAsc());
        model.addAttribute("tblCaracteristica", new tblCaracteristica());
        return "caracteristica"; // Nome do arquivo JSP para a página
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute tblCaracteristica caracteristica) {
        String userId = Cookies.getUserId();
        caracteristica.setAtivo(true);

        if (caracteristica.getId() != null && caracteristica.getId() != 0){
            Optional<tblCaracteristica> data = service.getById(caracteristica.getId());
            caracteristica.setDataCriacao(data.orElseThrow().getDataCriacao());
            caracteristica.setCriadoPor(data.get().getCriadoPor());
            caracteristica.setDataAlteracao(LocalDateTime.now());
            caracteristica.setAlteradoPor(Integer.parseInt(userId));
            service.update(caracteristica.getId(), caracteristica);
        }
        else {
            caracteristica.setDataCriacao(LocalDateTime.now());
            caracteristica.setCriadoPor(Integer.parseInt(userId));
            caracteristica.setDataAlteracao(LocalDateTime.now());
            caracteristica.setAlteradoPor(Integer.parseInt(userId));
            service.create(caracteristica);
        }
        return "redirect:/index?origem=caracteristica";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("listaCaracteristica", service.getActiveByNameAsc());
        model.addAttribute("tblCaracteristica", new tblCaracteristica());
        return "caracteristica";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userId = Cookies.getUserId();
        service.delete(id, Integer.parseInt(userId));
        return "redirect:/index?origem=caracteristica";
    }
}