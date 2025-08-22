package com.example.category.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.category.IService.ICategoryService;
import com.example.category.annotations.LogMethodParam;
import com.example.category.dao.Category;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private ICategoryService categoryService;
	
	@LogMethodParam
	@PostMapping("/bulk")
	public ResponseEntity<String> getFullName(@RequestBody(required = true) List<Category> categoryList){
		
		
		boolean status = this.categoryService.saveAllCategories(categoryList);
		
		return new ResponseEntity<String>(status ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@LogMethodParam
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable(required = true) Long id){
		
		Optional<Category> category = this.categoryService.findCategoryById(id);
		
		return category.isPresent() ? new ResponseEntity<>(category.get(), HttpStatus.OK) :
			new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@LogMethodParam
	@GetMapping("/all")
	public ResponseEntity<Object> getAll(){
		
		Object response = this.categoryService.getAll();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}

}
