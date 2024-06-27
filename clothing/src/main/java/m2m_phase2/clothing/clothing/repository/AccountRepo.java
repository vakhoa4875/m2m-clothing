package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

	Account findByUsernameOrEmail(@Param("username") String username,
								  @Param("email") String email);

}
