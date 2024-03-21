package m2m_phase2.clothing.clothing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.Userinfo;
import m2m_phase2.clothing.clothing.repository.AccountRepo;
import m2m_phase2.clothing.clothing.repository.UserinfoRepo;
import m2m_phase2.clothing.clothing.service.UserService;


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
	public Map<Account, Userinfo> getAll() {
		List<Userinfo> listIn4 = in4Repo.findAll();
		List<Account> listAcc = accRepo.findAll();

		Map<Account, Userinfo> map = new HashMap<>();

		for (int i = 0; i < listAcc.size(); i++) {
			map.put(listAcc.get(i), listIn4.get(i));
		}

		return map;
	}

}
