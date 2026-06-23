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
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento departamento;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas;
}
