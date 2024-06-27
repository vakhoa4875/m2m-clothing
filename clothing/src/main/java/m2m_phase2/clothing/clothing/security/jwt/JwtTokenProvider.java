package m2m_phase2.clothing.clothing.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m2m_phase2.clothing.clothing.security.data.CustomUserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;

    public String generateToken(CustomUserDetails userDetails) {
        var now = new Date();
        return JWT.create()
                .withSubject(userDetails.getAccount().getUserId().toString())
                .withIssuedAt(now)
                .withClaim("username", userDetails.getUsername())
                .withExpiresAt(new Date(now.getTime() + jwtProperties.getExpiration()))
                .sign(Algorithm.HMAC512(jwtProperties.getSecretKey()));
    }

    public DecodedJWT decodeToken(String token) {
        return JWT.require(Algorithm.HMAC512(jwtProperties.getSecretKey()))
                .build()
                .verify(token);
    }
}
