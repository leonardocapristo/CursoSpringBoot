package cursospringboot.springsecurity.services;

import cursospringboot.springsecurity.dto.request.RegisterUserRequest;
import cursospringboot.springsecurity.dto.response.RegisterUserResponse;
import cursospringboot.springsecurity.entities.User;
import cursospringboot.springsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    UserRepository repository;


    public RegisterUserResponse register(RegisterUserRequest registerUserRequest){

        User entity = new User();
        entity.setName(registerUserRequest.nome());
        entity.setEmail(registerUserRequest.email());
        entity.setPassword(registerUserRequest.senha());
        repository.save(entity);

        RegisterUserResponse registerUserResponse = new RegisterUserResponse(entity.getName(),entity.getEmail());

        return registerUserResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
