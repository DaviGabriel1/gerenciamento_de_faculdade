package com.gerenciamentofaculdade.gerenciamento_de_faculdade.academic.models.embedded;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.catalog.models.Curso;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class DisciplinaHistoricoId {
    @Column(name = "disciplina_id")
    private Long disciplinaId;
    @Column(name = "historico_id")
    private Long historicoId;
}
