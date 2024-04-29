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
public class SaidaProduto implements Serializable {
    private Integer saidaId;
    private Integer produtoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaidaProduto)) return false;
        SaidaProduto that = (SaidaProduto) o;
        return Objects.equals(getSaidaId(), that.getSaidaId()) &&
                Objects.equals(getProdutoId(), that.getProdutoId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSaidaId(), getProdutoId());
    }
}
