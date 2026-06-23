package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_token")
@EntityListeners(AuditingEntityListener.class)
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private OffsetDateTime deletadoEm;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
}
