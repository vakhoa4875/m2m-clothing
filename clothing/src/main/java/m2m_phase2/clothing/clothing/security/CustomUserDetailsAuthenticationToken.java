package m2m_phase2.clothing.clothing.security;

import m2m_phase2.clothing.clothing.security.data.CustomUserDetails;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class CustomUserDetailsAuthenticationToken extends AbstractAuthenticationToken {
    private final CustomUserDetails userDetails;

    public CustomUserDetailsAuthenticationToken(CustomUserDetails userDetails) {
        super(userDetails.getAuthorities());
        this.userDetails = userDetails;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public CustomUserDetails getPrincipal() {
        return userDetails;
    }
}
