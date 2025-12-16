package cursospringboot.webservices.controllers;

import cursospringboot.webservices.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    @GetMapping
    public ResponseEntity<User> findAll(){
        User u = new User(1L,"leo","leo@gmail.com",193701404,"leo123");
        return ResponseEntity.ok().body(u);
    }

}
