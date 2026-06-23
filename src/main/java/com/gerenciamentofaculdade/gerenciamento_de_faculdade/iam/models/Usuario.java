package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.models;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.enums.Discrepancia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UsuarioIam")
@Table(name = "iam_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 11)
    private String cpf;
    private String nome;
    private String sobrenome;
    @Column(unique = true)
    private String email;
    @Column(name = "senha_hash")
    private String senhaHash;
    @Column(name = "deletado_em")
    private OffsetDateTime deletadoEm;
    @Enumerated(EnumType.STRING)
    private Discrepancia discrepancia;

    @OneToOne(fetch = FetchType.EAGER)
    private Role role;

    @OneToMany(mappedBy = "usuario")
    private List<RefreshToken> refreshToken;

    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }
}
