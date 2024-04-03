package m2m_phase2.clothing.clothing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import m2m_phase2.clothing.clothing.entity.Account;
import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

	Account save(Account account);

	boolean isDisable(Account account);

	Account findByemail(String email);

	Account findByusername(String username);

	Account findByuserId(Integer id);

	List<Account> findAll();

	Account findByUsernameAndEmail(String username, String email);

}
