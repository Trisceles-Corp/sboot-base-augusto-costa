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
public class MovimentacaoProduto implements Serializable {
    private Integer movimentacaoId;
    private Integer produtoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovimentacaoProduto)) return false;
        MovimentacaoProduto that = (MovimentacaoProduto) o;
        return Objects.equals(getMovimentacaoId(), that.getMovimentacaoId()) &&
                Objects.equals(getProdutoId(), that.getProdutoId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovimentacaoId(), getProdutoId());
    }
}
