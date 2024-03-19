package m2m_phase2.clothing.clothing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import m2m_phase2.clothing.clothing.entity.Userinfo;

@Repository
public interface UserinfoRepo extends JpaRepository<Userinfo, Integer> {

	void delete(Userinfo entity);
	
	void deleteById(Integer id);

	List<Userinfo> findAll();

	@SuppressWarnings("unchecked")
	Userinfo save(Userinfo entity);

}
