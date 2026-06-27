package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_role")
    @Enumerated(EnumType.STRING)
    private EnumRole nomeRole;
    @OneToMany(mappedBy = "role")
    private List<Usuario> usuarios = new ArrayList<>();
}
