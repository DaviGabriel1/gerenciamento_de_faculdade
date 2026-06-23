package com.gerenciamentofaculdade.gerenciamento_de_faculdade.profile.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity(name = "ProfessorProfile")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile_professor")
public class Professor {
    @Id
    private Long id;
}
