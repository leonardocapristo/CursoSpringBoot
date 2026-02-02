package cursospringboot.springsecurity.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(@NotEmpty(message = "name é obrigatório")  String name,
                                  @NotEmpty(message = "email é obrigatório") String email,
                                  @NotEmpty(message = "password é obrigatória") String password) {
}
