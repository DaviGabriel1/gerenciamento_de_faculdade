package com.gerenciamentofaculdade.gerenciamento_de_faculdade.catalog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "departamento")
    private List<Curso> cursos;
    @OneToMany(mappedBy = "departamento")
    private List<Disciplina> disciplinas;
}
