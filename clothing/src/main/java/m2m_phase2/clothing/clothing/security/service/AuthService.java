package m2m_phase2.clothing.clothing.security.service;

import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.security.data.CustomUserDetails;
import m2m_phase2.clothing.clothing.security.data.dto.LoginResponseDTO;
import m2m_phase2.clothing.clothing.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
            var userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getAccount();
        }
        return null;
    }
}
