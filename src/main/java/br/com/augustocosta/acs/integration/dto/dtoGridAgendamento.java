package br.com.augustocosta.acs.integration.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class dtoGridAgendamento {
    private String horario;
    private Map<String, String> colaboradores;

    public dtoGridAgendamento() {
    }
}