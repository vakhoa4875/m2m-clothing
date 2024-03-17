package m2m_phase2.clothing.clothing.service;

import m2m_phase2.clothing.clothing.entity.Account;

public interface AccountService {

	Account saveAccount(Account account);
	
	Account findByEmail(String email);
}
