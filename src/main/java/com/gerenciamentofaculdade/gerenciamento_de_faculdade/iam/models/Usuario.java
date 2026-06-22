package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.models;

import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.enums.Discrepancia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
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

    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }
}
