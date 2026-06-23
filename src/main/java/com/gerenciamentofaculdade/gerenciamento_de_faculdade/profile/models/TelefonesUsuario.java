package com.gerenciamentofaculdade.gerenciamento_de_faculdade.profile.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "telefones_usuario")
@EntityListeners(AuditingEntityListener.class)
public class TelefonesUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telefone;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoTelefone tipoTelefone;

    @CreatedDate
    private LocalDateTime criadoEm;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime atualizadoEm;
}
