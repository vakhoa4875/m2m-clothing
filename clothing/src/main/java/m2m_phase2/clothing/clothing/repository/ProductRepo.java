package m2m_phase2.clothing.clothing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import m2m_phase2.clothing.clothing.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	List<Product> findAll();
}	
