package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_token")
@SQLDelete(sql = "UPDATE refresh_token SET deletado_em = CURRENT_TIMESTAMP WHERE id = ?")
//Filtra automaticamente os registros deletados em todas as buscas (SELECT)
@SQLRestriction("deletado_em IS NULL")
@EntityListeners(AuditingEntityListener.class)
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid;
    @Column(name = "criado_em")
    private LocalDateTime criadoEm;
    @Column(name = "expira_em")
    private LocalDateTime expiraEm;
    @Column(name = "deletado_em")
    private LocalDateTime deletadoEm;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
}
