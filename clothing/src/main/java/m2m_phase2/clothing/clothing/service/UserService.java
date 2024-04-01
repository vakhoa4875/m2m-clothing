package m2m_phase2.clothing.clothing.service;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.DTO.AccountDto;
import org.springframework.stereotype.Service;

import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.Userinfo;

@Service
public interface UserService {

	void deleteById(Integer id);

	Map<Account, Userinfo> getAll();

	String checkUserAdminRole(AccountDto accountDto);

	void saveToSession(HttpSession httpSession, AccountDto accountDto);

	void  save(Account acc, Userinfo info);

}
