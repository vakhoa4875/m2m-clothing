package m2m_phase2.clothing.clothing.controller.api;

import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountApi {

    @Autowired
    AccountServiceImpl accountImpl;

    @GetMapping("/getAccountByUsernameAndEmail")
    public Account findByUsernameAndEmail(@RequestParam("username") String username, @RequestParam("email") String email){
       return accountImpl.findByUsernameAndEmail(username,email);
    }

}
