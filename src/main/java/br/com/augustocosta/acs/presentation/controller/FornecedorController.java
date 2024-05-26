package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.service.EnderecoService;
import br.com.augustocosta.acs.business.service.UsuarioService;
import br.com.augustocosta.acs.integration.dto.dtoUsuario;
import br.com.augustocosta.acs.integration.entity.tblEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dtoUsuario", new dtoUsuario());
        return "fornecedor";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaFornecedores", usuarioService.getAllByPerfil(6));
        model.addAttribute("listaPerfil", usuarioService.getAllActivesByPerfil());
        model.addAttribute("listCargos", usuarioService.getAllActivesByCargo());
        model.addAttribute("dtoUsuario", new dtoUsuario());
        return "fornecedor";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute dtoUsuario dados) {
        dados.setDdiCelular(55);
        dados.setDdiTelefone(55);
        dados.setCargoId(16);
        dados.setPerfilId(6);
        dados.setGenero('O');
        dados.setSenha("123456");
        dados.setDataNascimento(LocalDate.now());

        if (dados.getUsuarioId() != null && dados.getUsuarioId() != 0){
            tblEndereco saveEndereco = enderecoService.updateDto(dados);
            usuarioService.updateDto(saveEndereco, dados);
        }
        else {
            usuarioService.createDto(enderecoService.createDto(dados), dados);
        }

        return "redirect:/index";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        usuarioService.delete(id, 1);
        return "redirect:/index";
    }
}