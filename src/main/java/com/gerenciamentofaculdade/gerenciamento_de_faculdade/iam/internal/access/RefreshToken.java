package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_token")
// Sobrescreve o comportamento do DELETE nativo
@SQLDelete(sql = "UPDATE refresh_token SET deletadoEm = CURRENT_TIMESTAMP WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String token;
    private OffsetDateTime deletadoEm;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
}
