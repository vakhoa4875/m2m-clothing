package m2m_phase2.clothing.clothing.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import m2m_phase2.clothing.clothing.security.data.CustomUserDetails;
import m2m_phase2.clothing.clothing.service.AccountService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtToUserDetailsConverter {
    private final AccountService accountService;

    @SneakyThrows
    public CustomUserDetails convert(DecodedJWT decodedJWT) {
        var account = accountService.findByUsername(decodedJWT.getClaim("username").asString());
        return CustomUserDetails.builder()
                .account(account)
                .build();
    }
}
