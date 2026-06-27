package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam;


import com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam.internal.identity.Discrepancia;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 8, max = 30, message = "A senha deve ter entre 8 e 30 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).*$",
            message = "A senha deve conter pelo menos uma letra maiúscula, uma minúscula e um caractere especial"
    )
    private String senha;
}
