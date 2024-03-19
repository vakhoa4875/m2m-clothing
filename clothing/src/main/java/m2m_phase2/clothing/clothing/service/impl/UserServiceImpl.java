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
		System.out.println(session.getAttributeNames());
		return 0==2;
	}

	@Override
	public void deleteById(Integer id) {
		in4Repo.deleteById(id);
		accRepo.deleteById(id);
	}

	@Override
	public Userinfo save(Userinfo entity) {
		return in4Repo.save(entity);
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
