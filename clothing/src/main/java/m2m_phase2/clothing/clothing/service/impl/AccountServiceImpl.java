package m2m_phase2.clothing.clothing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.repository.AccountRepo;
import m2m_phase2.clothing.clothing.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo repo;

	@Override
	public Account saveAccount(Account account) {
		// TODO Auto-generated method stub
		return repo.save(account);
	}

}
