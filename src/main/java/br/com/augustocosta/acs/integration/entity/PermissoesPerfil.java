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
public class PermissoesPerfil implements Serializable {
    private Integer perfilId;
    private Integer permissaoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissoesPerfil that = (PermissoesPerfil) o;
        return Objects.equals(perfilId, that.perfilId) &&
                Objects.equals(permissaoId, that.permissaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(perfilId, permissaoId);
    }
}
