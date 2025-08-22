package com.example.category.IService;

import java.util.List;
import java.util.Optional;

import com.example.category.dao.Category;

public interface ICategoryService {

	public boolean saveAllCategories(List<Category> categoryList);
	
	public Optional<Category> findCategoryById(Long id);
	
	public Object getAll();
}
