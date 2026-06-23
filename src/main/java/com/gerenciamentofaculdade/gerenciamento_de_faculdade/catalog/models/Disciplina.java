package com.gerenciamentofaculdade.gerenciamento_de_faculdade.catalog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String sigla;
    @Column(name = "num_alunos")
    private Integer numAlunos;
    @Column(name = "carga_horaria")
    private Integer cargaHoraria;
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento departamento;
    @ManyToMany(mappedBy = "disciplinas")
    private List<Curso> cursos;
    @ManyToMany
    @JoinTable(
            name = "cat_disciplina_dependencias", // Nome da tabela intermediária no banco
            joinColumns = @JoinColumn(name = "disciplina_id"), // A disciplina principal
            inverseJoinColumns = @JoinColumn(name = "pre_requisito_id") // A disciplina exigida
    )
    private List<Disciplina> preRequisitos = new ArrayList<>();

    public void adicionarPreRequisito(Disciplina preRequisito) {
        // Validação básica para a disciplina não ser pré-requisito dela mesma
        if (this.id != null && this.id.equals(preRequisito.getId())) {
            throw new IllegalArgumentException("Uma disciplina não pode depender de si mesma.");
        }
        this.preRequisitos.add(preRequisito);
    }
}
