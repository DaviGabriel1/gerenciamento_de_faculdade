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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "endereco_aluno")
public class EnderecoAluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "num_rua")
    private String numRua;
    @Column(name = "nome_rua")
    private String nomeRua;
    private String complemento;
    private String cep;
    @CreatedDate
    private LocalDateTime criadoEm;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime atualizadoEm;
    private LocalDateTime deletadoEm;
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoLogradouro tipoLogradouro;
    @OneToOne(mappedBy = "endereco_aluno")
    private Aluno aluno;
}
