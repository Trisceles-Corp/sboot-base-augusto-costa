package br.com.augustocosta.acs.integration.projections;

import java.sql.SQLException;
import java.util.Map;

public interface prjGridAgendamento {
    String getHorario() throws SQLException;
    Map<String, String> getColaboradores() throws SQLException;
}