package cursospringboot.springsecurity.controller;

import cursospringboot.springsecurity.dto.request.LoginRequest;
import cursospringboot.springsecurity.dto.request.RegisterUserRequest;
import cursospringboot.springsecurity.dto.response.LoginResponse;
import cursospringboot.springsecurity.dto.response.RegisterUserResponse;
import cursospringboot.springsecurity.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    UserServices services;


    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login (@Valid @RequestBody LoginRequest loginRequest){

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequest.email(),loginRequest.password());
        Authentication auth = authenticationManager.authenticate(userAndPass);

        return null;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterUserResponse> register (@Valid @RequestBody RegisterUserRequest registerUserRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(services.register(registerUserRequest));
    }
}
