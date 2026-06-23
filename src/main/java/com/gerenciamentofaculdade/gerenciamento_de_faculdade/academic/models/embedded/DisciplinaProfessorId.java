package com.gerenciamentofaculdade.gerenciamento_de_faculdade.academic.models.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DisciplinaProfessorId {
    @Column(name = "disciplina_id")
    private Long disciplinaId;
    @Column(name = "professor_id")
    private Long professorId;
}
