package com.gerenciamentofaculdade.gerenciamento_de_faculdade.academic.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaHistorico {
    @EmbeddedId
    private DisciplinaHistoricoId disciplinaHistoricoId;
    private Double nota;
    private String observacao;
    private Integer frequencia;
}
