package m2m_phase2.clothing.clothing.security.service;

import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.security.data.CustomUserDetails;
import m2m_phase2.clothing.clothing.security.data.dto.LoginResponseDTO;
import m2m_phase2.clothing.clothing.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDTO authenticate(String username, String password) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var userDetails = (CustomUserDetails) authentication.getPrincipal();
        var token = jwtTokenProvider.generateToken(userDetails);
        return LoginResponseDTO.builder()
                .accessToken(token)
                .build();
    }

    public Account getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof OAuth2User oAuth2User) {
                var sub = oAuth2User.getAttribute("sub");
                System.out.println(sub);
                System.out.println(oAuth2User);
            } else if (authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
                System.out.println(customUserDetails);
                return customUserDetails.getAccount();
            }
        }
        return null;
    }

    public String getCurrentUserEmail() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof OAuth2User oAuth2User) {
                return oAuth2User.getAttribute("email");
            } else if (authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
                var account = customUserDetails.getAccount();
                return account.getEmail();
            }
        }
        return null;
    }
}
