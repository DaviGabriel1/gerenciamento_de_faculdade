package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Codigo {
    @Id
    @GeneratedValue
    private Long id;
    private String codigo;
    private LocalDateTime criadoEm;
    private LocalDateTime ExpiraEm;
    private LocalDateTime validadoEm;
    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuario;
}
