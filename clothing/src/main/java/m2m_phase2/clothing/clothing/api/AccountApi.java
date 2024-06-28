package m2m_phase2.clothing.clothing.api;

import lombok.RequiredArgsConstructor;
import m2m_phase2.clothing.clothing.constant.AccountEnum;
import m2m_phase2.clothing.clothing.data.dto.AccountDto;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.security.service.AuthService;
import m2m_phase2.clothing.clothing.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/admin/account")
@RequiredArgsConstructor
public class AccountApi {
    private final AccountService accountService;
    private final AuthService authService;

    @GetMapping("/getByUsernameAndEmail")
    public Account doGetByUsernameAndEmail(@RequestParam("username") String username, @RequestParam("email") String email) {
        return accountService.findByUsernameAndEmail(username, email);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doPostCreateAccount(@RequestBody AccountDto accountDto) {
        String insertStatus;
        try {
            insertStatus = accountService.createAccount(accountDto);
        } catch (SQLException | NullPointerException e) {
            insertStatus = AccountEnum.error.getValue();
        }
        return ResponseEntity.ok(insertStatus);
    }

    @GetMapping("/isAuthenticated")
    public boolean isLoggedIn() {
        return authService.getCurrentUserEmail() != null;
    }
}
