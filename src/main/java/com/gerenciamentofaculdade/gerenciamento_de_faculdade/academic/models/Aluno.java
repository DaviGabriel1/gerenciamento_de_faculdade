package com.gerenciamentofaculdade.gerenciamento_de_faculdade.academic.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity(name = "AlunoAcademic")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "academic_aluno")
public class Aluno {
    @Id
    private Long id;
    private String ra;

}
