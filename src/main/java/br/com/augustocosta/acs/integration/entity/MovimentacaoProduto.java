package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
@Getter // Cria automaticamente os getters para todos os campos
@Setter // Cria automaticamente os setters para todos os campos
@NoArgsConstructor // Cria um construtor sem argumentos
@AllArgsConstructor // Cria um construtor com todos os argumentos
public class MovimentacaoProduto implements Serializable {
    private Integer movimentacaoId;
    private Integer produtoId;
}
