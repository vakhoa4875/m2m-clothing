package m2m_phase2.clothing.clothing.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.security.data.dto.LoginRequestDTO;
import m2m_phase2.clothing.clothing.security.data.dto.LoginResponseDTO;
import m2m_phase2.clothing.clothing.security.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/secured")
    public String secured() {
        var email = authService.getCurrentUserEmail();
        System.out.println("/secured");
        return "If you see this, then you are logged in as user: "
                + email;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return authService.authenticate(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }

    @DeleteMapping("/deleteMethod")
    public String doDelete() {
        return "deleted";
    }

    @PostMapping("/post")
    public Integer doPost() {
        return 1;
    }

}
