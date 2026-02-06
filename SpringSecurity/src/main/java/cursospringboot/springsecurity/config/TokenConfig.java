package cursospringboot.springsecurity.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cursospringboot.springsecurity.entities.User;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenConfig {

    private String tokenSecret = "secret";

    public String generateToken(User user){
        return JWT.create()
                .withClaim("userId", user.getId())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .sign(Algorithm.HMAC256(tokenSecret));
    }
}
