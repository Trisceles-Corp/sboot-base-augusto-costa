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
public class VendaProduto implements Serializable {
    private Integer vendaId;
    private Integer produtoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendaProduto that = (VendaProduto) o;
        return Objects.equals(vendaId, that.vendaId) &&
                Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendaId, produtoId);
    }
}
