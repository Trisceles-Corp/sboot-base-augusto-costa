package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComandaPagamento implements Serializable {
    private Integer comandaId;
    private Integer formaPagamentoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComandaPagamento)) return false;
        ComandaPagamento that = (ComandaPagamento) o;
        return Objects.equals(getComandaId(), that.getComandaId()) &&
                Objects.equals(getFormaPagamentoId(), that.getFormaPagamentoId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getComandaId(), getFormaPagamentoId());
    }
}
