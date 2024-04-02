package m2m_phase2.clothing.clothing.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.DTO.AccountDto;
import m2m_phase2.clothing.clothing.entity.DTO.UserDto;
import m2m_phase2.clothing.clothing.entity.model.UserM;
import org.springframework.stereotype.Service;

import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.Userinfo;

@Service
public interface UserService {
	byte updateUser(UserDto userDto) throws SQLException;
	void deleteById(Integer id);

	Map<Account, Userinfo> getAll();

	String checkUserAdminRole(AccountDto accountDto);

	void saveToSession(HttpSession httpSession, AccountDto accountDto);

	void  save(Account acc, Userinfo info);

    UserM findUserById(int id) throws SQLException;
}
