package cursospringboot.springsecurity.services;

import cursospringboot.springsecurity.config.TokenConfig;
import cursospringboot.springsecurity.dto.request.RegisterUserRequest;
import cursospringboot.springsecurity.dto.response.RegisterUserResponse;
import cursospringboot.springsecurity.entities.User;
import cursospringboot.springsecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenConfig tokenConfig;


    public RegisterUserResponse register(RegisterUserRequest registerUserRequest){

        User entity = new User();
        entity.setName(registerUserRequest.name());
        entity.setEmail(registerUserRequest.email());
        entity.setPassword(passwordEncoder.encode(registerUserRequest.password()));
        repository.save(entity);

        RegisterUserResponse registerUserResponse = new RegisterUserResponse(entity.getName(),entity.getEmail());

        return registerUserResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public String generateToken(User user){
        return tokenConfig.generateToken(user);
    }
}
