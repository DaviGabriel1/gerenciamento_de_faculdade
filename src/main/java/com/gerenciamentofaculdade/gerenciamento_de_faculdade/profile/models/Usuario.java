package com.gerenciamentofaculdade.gerenciamento_de_faculdade.profile.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "UsuarioProfile")
@Table(name = "profile_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
    @Id
    private Long id;
    private String nome;
    private String sobrenome;
    @Column(unique = true, length = 11)
    private String cpf;
    @Column(unique = true)
    private String email;
    private Character sexo;
    @OneToMany(mappedBy = "usuario")
    private List<TelefonesUsuario> telefonesUsuario;
    @CreatedDate
    private LocalDateTime criadoEm;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime atualizadoEm;

}
