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
public class CompraProduto implements Serializable {
    private Integer compraId;
    private Integer produtoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompraProduto)) return false;
        CompraProduto that = (CompraProduto) o;
        return Objects.equals(getCompraId(), that.getCompraId()) &&
                Objects.equals(getProdutoId(), that.getProdutoId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompraId(), getProdutoId());
    }
}
