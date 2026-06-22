package com.gerenciamentofaculdade.gerenciamento_de_faculdade.profile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
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

}
