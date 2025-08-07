package com.mateus.encurta_link.authentication.types;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para registro de novo usuário")
public record UserRegisterRequest(
    @Schema(
        description = "Email do usuário",
        example = "usuario@exemplo.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato de email inválido")
    String email,

    @Schema(
        description = "Senha do usuário (mínimo 6 caracteres)",
        example = "senhaSegura123",
        requiredMode = Schema.RequiredMode.REQUIRED,
        minLength = 6
    )
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    String password
) {}