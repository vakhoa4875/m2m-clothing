package m2m_phase2.clothing.clothing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import m2m_phase2.clothing.clothing.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

	 Account save(Account account);
	 
	 Account findByEmail(String email);
	
}
