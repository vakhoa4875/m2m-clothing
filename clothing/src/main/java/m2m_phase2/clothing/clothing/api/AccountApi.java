package m2m_phase2.clothing.clothing.api;

import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.data.model.UserM;
import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class AccountApi {

    @Autowired
    AccountServiceImpl accountImpl;

    @Autowired
    AccountService account;
    @GetMapping("/getAccountByUsernameAndEmail")
    public Account findByUsernameAndEmail(@RequestParam("username") String username, @RequestParam("email") String email){
       return accountImpl.findByUsernameAndEmail(username,email);
    }

//    @GetMapping("/getUserByUsernameAndEmail")
//    public ResponseEntity<?> doGetUserByUsernameAndEmail(UserDto userDto) {
//        UserM user;
//        try {
//            user = account.getUserByUsernameAndEmail(userDto);
//        } catch (SQLException e) {
//            System.out.println("Call API Failed: /api-public/users/getUserByUsernameAndEmail");
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok(user);
//    }

}
