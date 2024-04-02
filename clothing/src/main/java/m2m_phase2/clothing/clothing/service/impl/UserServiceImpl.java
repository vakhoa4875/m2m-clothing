package m2m_phase2.clothing.clothing.service.impl;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.DTO.AccountDto;
import m2m_phase2.clothing.clothing.entity.DTO.UserDto;
import m2m_phase2.clothing.clothing.entity.Userinfo;
import m2m_phase2.clothing.clothing.entity.model.AccountM;
import m2m_phase2.clothing.clothing.entity.model.UserM;
import m2m_phase2.clothing.clothing.repository.AccountRepo;
import m2m_phase2.clothing.clothing.repository.UserinfoRepo;
import m2m_phase2.clothing.clothing.service.UserService;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountRepo accRepo;

    @Autowired
    private UserinfoRepo in4Repo;

    @Autowired
    private HttpSession session;

    public boolean isAdminAuth() {
        return false;
    }

    @Override
    public byte updateUser(UserDto userDto) throws SQLException {
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        Account acc = accRepo.findByuserId(id);
        acc.setDisable(true);
        accRepo.save(acc);
    }

    @Override
    public void save(Account acc, Userinfo info) {
        accRepo.save(acc);
        in4Repo.save(info);
    }

    @Override
    public UserM findUserById(int id) throws SQLException {
        return UserM.convertAccountToAccountM(accRepo.findByuserId(id));
    }

    @Override
    public Map<Account, Userinfo> getAll() {
        List<Userinfo> listIn4 = in4Repo.findAll();
        List<Account> listAcc = accRepo.findAll();

        Map<Account, Userinfo> map = new HashMap<>();

        for (int i = 0; i < listAcc.size(); i++) {
            map.put(listAcc.get(i), listIn4.get(i));
        }

        return map;
    }

    @Override
    public String checkUserAdminRole(AccountDto accountDto) {// remember to create constant class
//        System.out.println("accountDto toString: " + accountDto.toString() +
//                "\n" + "accountE: " + accRepo.findByemail(accountDto.getEmail()).toString());
        AccountM accountM = AccountM.convertAccountToAccountM(accRepo.findByemail(accountDto.getEmail()));
        if (accountM == null) {
            return "Email not registered yet!!";
        }
        if (!PasswordEncoderUtil.verifyPassword(accountDto.getPassword(), accountM.getHashedPassword())) {
            System.out.println(PasswordEncoderUtil.verifyPassword("123", "e71f60c54423de2e70cfaef0b89e565d1be007c038854dd206dff45491af6b988fe025a688fbe218") +
                    "/naccountE verify: " + PasswordEncoderUtil.verifyPassword("123", "47be8c36c20369f8ca8f665267661c0e873a4b17370a42809b18300864434fb35d880be64134fb65"));
            return "Wrong password! Please check & try again!!";
        }
        if (!accountM.getIsAdmin()) {
            return "Authorization failed!!";
        }
        return "Login Succeed";
    }

    @Override
    public void saveToSession(HttpSession httpSession, AccountDto accountDto) {
        accountDto.setHashedPassword(accountDto.getPassword());
        System.out.println("status: " + checkUserAdminRole(accountDto));
        if (checkUserAdminRole(accountDto).equals("Login Succeed")) {
            httpSession.setAttribute("adminToken", PasswordEncoderUtil.encodePassword(accountDto.getUsername() + accountDto.getEmail()));
            System.out.println("session value: " + httpSession.getAttribute("adminToken") +
                    "\nraw value: " + PasswordEncoderUtil.encodePassword(accountDto.getUsername() + accountDto.getEmail()));
        } else {
            httpSession.removeAttribute("adminToken");
            System.out.println("fail session");
        }
    }

}
