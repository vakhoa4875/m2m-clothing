package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.entity.Account;

public interface AccountService {

	Account saveAccount(Account account);
	Account findByusername(String username);
	
	Account findByemail(String email);
	
	boolean isAdmin(Account account);
}
