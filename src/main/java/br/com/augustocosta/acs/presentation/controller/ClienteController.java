package br.com.augustocosta.acs.presentation.controller;

import br.com.augustocosta.acs.business.util.Cookies;
import br.com.augustocosta.acs.business.service.EnderecoService;
import br.com.augustocosta.acs.business.service.UsuarioService;
import br.com.augustocosta.acs.integration.dto.dtoUsuario;
import br.com.augustocosta.acs.integration.entity.tblEndereco;
import br.com.augustocosta.acs.integration.entity.tblFormasPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dtoUsuario", new dtoUsuario());
        return "cliente";
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("listaClientes", usuarioService.getAllByPerfil(4));
        model.addAttribute("listaPerfil", usuarioService.getAllActivesByPerfil());
        model.addAttribute("listCargos", usuarioService.getAllActivesByCargo());
        model.addAttribute("dtoUsuario", new dtoUsuario());
        return "cliente";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute dtoUsuario dados) {
        String cpfCnpj = dados.getCpfCnpj().replaceAll("\\D", "");

        if (usuarioService.getByCpfCnpj(cpfCnpj).size() >= 1){

        }
        dados.setCpfCnpj(cpfCnpj);
        dados.setDdiCelular(55);
        dados.setDdiTelefone(55);
        dados.setCargoId(1);
        dados.setPerfilId(4);
        if (dados.getUsuarioId() != null && dados.getUsuarioId() != 0){
            tblEndereco saveEndereco = enderecoService.updateDto(dados);
            usuarioService.updateDto(saveEndereco, dados);
        }
        else {
            dados.setSenha("123456");
            dados.setCargoId(1);
            dados.setPerfilId(4);
            usuarioService.createDto(enderecoService.createDto(dados), dados);
        }
        return "redirect:/index?origem=cliente";
    }

    @GetMapping("/verificarCpfCnpj/{cpfCnpj}")
    public ResponseEntity<Boolean>  verificarCpfCnpj(@PathVariable String cpfCnpj) {
        boolean exists = usuarioService.getByCpfCnpj(cpfCnpj).size() >= 1;
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String userCookie = Cookies.getUserId();
        if(userCookie == null){ userCookie = "1"; }
        int activeUserId = Integer.parseInt(userCookie) ;
        usuarioService.delete(id, activeUserId);
        return "redirect:/index?origem=cliente";
    }
}