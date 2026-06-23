package com.gerenciamentofaculdade.gerenciamento_de_faculdade.academic.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer periodo;
    private Integer numAlunos;
    @JoinColumn(name = "curso_id", unique = true)
    private Long cursoId;
    @CreatedDate
    private LocalDateTime criadoEm;
    @OneToMany(mappedBy = "turma")
    private List<Aluno> alunos;
}
