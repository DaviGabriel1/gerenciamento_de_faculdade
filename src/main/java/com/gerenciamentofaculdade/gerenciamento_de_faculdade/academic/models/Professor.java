package com.gerenciamentofaculdade.gerenciamento_de_faculdade.academic.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity(name = "ProfessorAcademic")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "academic_professor")
public class Professor {
    @Id
    private Long id;
    @Column(name = "departamento_id")
    private Long departamentoId;
}
