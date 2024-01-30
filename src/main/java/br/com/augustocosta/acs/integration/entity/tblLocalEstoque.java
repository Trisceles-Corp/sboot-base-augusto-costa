package br.com.augustocosta.acs.integration.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "tbl_localestoque")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class tblLocalEstoque {

    @Id @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LocalEstoqueId")
    private Integer id;

    @Column(name = "DescricaoLocal", nullable = false)
    private String descricaoLocal;

    @Column(name = "Ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "DataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DataAlteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(name = "CriadoPor", nullable = false)
    private Integer criadoPor;

    @Column(name = "AlteradoPor", nullable = false)
    private Integer alteradoPor;
}