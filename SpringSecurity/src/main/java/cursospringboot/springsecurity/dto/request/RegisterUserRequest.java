package cursospringboot.springsecurity.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(@NotEmpty(message = "nome é obrigatório")  String nome,
                                  @NotEmpty(message = "email é obrigatório") String email,
                                  @NotEmpty(message = "senha é obrigatória") String senha) {
}
