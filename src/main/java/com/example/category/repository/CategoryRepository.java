package com.example.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.category.dao.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	

}
