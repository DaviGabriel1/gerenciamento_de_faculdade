package com.gerenciamentofaculdade.gerenciamento_de_faculdade.catalog.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinaDepende {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private Disciplina disciplina;
    @ManyToOne
    private Disciplina dependencia;
}
