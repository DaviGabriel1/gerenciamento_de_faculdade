package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam;


import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.Discrepancia;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "O CPF é obrigatório")
    @NotBlank(message = "O CPF não pode estar em branco")
    @CPF(message = "Informe um CPF válido")
    private String cpf;
    @NotEmpty(message = "O nome é obrigatório")
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;
    @NotEmpty(message = "O sobrenome é obrigatório")
    @NotBlank(message = "O sobrenome não pode estar em branco")
    private String sobrenome;
    @Email(message = "Informe um e-mail válido")
    @NotEmpty(message = "O e-mail é obrigatório")
    @NotBlank(message = "O e-mail não pode estar em branco")
    private String email;
    @NotEmpty(message = "A senha é obrigatória")
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 8, message = "a senha deve ter no minimo 8 caracteres")
    private String senha;
}
