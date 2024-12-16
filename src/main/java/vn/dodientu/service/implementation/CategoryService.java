package vn.dodientu.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.edu.vn.entity.Category;
import hcmute.edu.vn.repository.CategoryRepository;
import hcmute.edu.vn.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAllCategory() {
		// TODO Auto-generated method stub
		return this.categoryRepository.findAll();
	}

	@Override
	public Category findCategoryById(Integer id_category) {
		// TODO Auto-generated method stub
		return this.categoryRepository.getReferenceById(id_category);
	}
	
	
}
