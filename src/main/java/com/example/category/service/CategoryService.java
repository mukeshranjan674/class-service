package com.example.category.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.category.IService.ICategoryService;
import com.example.category.dao.Category;
import com.example.category.repository.CategoryRepository;
import com.fasterxml.jackson.annotation.JsonInclude;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean saveAllCategories(List<Category> categoryList) {
		try {
			this.categoryRepository.saveAll(categoryList);
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public Optional<Category> findCategoryById(Long id) {
		Optional<Category> category = this.categoryRepository.findById(id);
		return category;
	}

	@Override
	public Object getAll() {
		List<Category> categoryList = this.categoryRepository.findAll();
		
		record Entity(String name, 
				@JsonInclude(JsonInclude.Include.NON_EMPTY) List<Entity> subClasses) {};
		List<Entity> root = new ArrayList<>();
		
		Map<Long, Entity> map = categoryList.stream()
				.collect(Collectors.toMap(Category :: getId, t -> new Entity(t.getName(), new ArrayList<>())));
		
		for(Category category : categoryList) {
			Entity current = map.get(category.getId());
			if(category.getParentId() == 0) {
				root.add(current);
			}
			else {
				Entity parent = map.get(category.getParentId());
				if(parent != null)
					parent.subClasses.add(current);
			}
		}
		
		return root;
	}

	@Override
	public void deleteAll() {
		
		this.categoryRepository.deleteAll();
		
	}

}
