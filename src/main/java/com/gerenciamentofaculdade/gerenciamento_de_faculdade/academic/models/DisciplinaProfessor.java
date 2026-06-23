package com.gerenciamentofaculdade.gerenciamento_de_faculdade.academic.models;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.academic.models.embedded.DisciplinaProfessorId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaProfessor {
    @EmbeddedId
    private DisciplinaProfessorId id;
}
