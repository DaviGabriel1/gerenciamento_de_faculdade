package com.gerenciamentofaculdade.gerenciamento_de_faculdade.academic.models;

import jakarta.persistence.*;
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
    @Column(unique = true)
    private String ra;
    @ManyToOne(fetch = FetchType.LAZY)
    private Turma turma;
    @Column(name = "curso_id", unique = true)
    private Long cursoId;
    @OneToOne(mappedBy = "usuarioId")
    private Historico historico;
}
