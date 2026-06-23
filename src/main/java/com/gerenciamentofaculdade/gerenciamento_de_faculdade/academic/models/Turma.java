package com.gerenciamentofaculdade.gerenciamento_de_faculdade.academic.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

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
    @JoinColumn(name = "curso_id")
    private Long cursoId;
    @CreatedDate
    private LocalDateTime criadoEm;
}
