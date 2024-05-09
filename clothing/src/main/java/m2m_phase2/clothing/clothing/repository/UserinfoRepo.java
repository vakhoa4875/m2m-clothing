package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserinfoRepo extends JpaRepository<Userinfo, Integer> {

    List<Userinfo> findAll();

    @SuppressWarnings("unchecked")
    Userinfo save(Userinfo entity);

}
