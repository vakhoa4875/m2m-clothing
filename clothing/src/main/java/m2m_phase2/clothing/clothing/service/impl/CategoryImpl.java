package m2m_phase2.clothing.clothing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import m2m_phase2.clothing.clothing.data.entity.Category;
import m2m_phase2.clothing.clothing.repository.CategoryRepo;
import m2m_phase2.clothing.clothing.service.CategoryService;

@Service
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo repo;
	
	
	@Override
	public List<Category> findAll() {
		return repo.findAll();
	}

}
