package com.gerenciamentofaculdade.gerenciamento_de_faculdade.profile.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity(name = "AlunoProfile")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile_aluno")
public class Aluno {
    @Id
    private Long id;
    private String ra;
    @OneToOne(fetch = FetchType.EAGER)
    private EnderecoAluno enderecoAluno;
}
