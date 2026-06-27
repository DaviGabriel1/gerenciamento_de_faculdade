package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationRequest {
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
