package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.access.RefreshToken;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UsuarioIam")
@Table(name = "iam_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements UserDetails, Principal {
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
    private Boolean habilitado;
    @Column(name = "conta_trancada")
    private Boolean contaTrancada;

    @OneToOne(fetch = FetchType.EAGER)
    private Role role;

    @OneToMany(mappedBy = "usuario")
    private List<RefreshToken> refreshToken;

    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // busca a role do usuario
        return List.of(new SimpleGrantedAuthority(role.getNomeRole().getNomeRole()));
    }

    @Override
    public @Nullable String getPassword() {
        return senhaHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !contaTrancada;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return habilitado;
    }

    public String fullName() {
        return nome + " " + sobrenome;
    }
}
