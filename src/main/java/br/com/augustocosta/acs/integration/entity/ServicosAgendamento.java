package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import java.io.Serializable;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicosAgendamento implements Serializable {
    private Integer servicoId;
    private Integer agendamentoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicosAgendamento that = (ServicosAgendamento) o;
        return Objects.equals(servicoId, that.servicoId) &&
                Objects.equals(agendamentoId, that.agendamentoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(servicoId, agendamentoId);
    }
}
