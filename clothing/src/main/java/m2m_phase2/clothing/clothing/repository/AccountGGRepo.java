package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.AccountGGE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountGGRepo extends JpaRepository<AccountGGE,Integer> {

     AccountGGE save(AccountGGE accountGGE);

     AccountGGE findByemailGG(String email);
}
