package vn.dodientu.service.interfaces;

import java.util.List;

import hcmute.edu.vn.entity.Category;

public interface ICategoryService {
	List<Category> findAllCategory();
	Category findCategoryById(Integer id_category);
}
