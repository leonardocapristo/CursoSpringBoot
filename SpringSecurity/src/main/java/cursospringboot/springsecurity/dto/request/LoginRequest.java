package cursospringboot.springsecurity.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty(message = "email é obrigatório") String email,
                           @NotEmpty(message = "senha é obrigatória") String password) {
}
