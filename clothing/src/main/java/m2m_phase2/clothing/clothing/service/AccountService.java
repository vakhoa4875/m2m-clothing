package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.model.UserM;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {

	Account saveAccount(Account account);
	Account findByusername(String username);
	
	Account findByemail(String email);
	

	Account findByuserId(Integer id);

	boolean isDisable(Account account);

	List<UserM> findAll() throws SQLException;

	Account findByUsernameAndEmail(String username, String email);

}
