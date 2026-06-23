package com.gerenciamentofaculdade.gerenciamento_de_faculdade.profile.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Coordenador {
    @Id
    private Long id;
}
