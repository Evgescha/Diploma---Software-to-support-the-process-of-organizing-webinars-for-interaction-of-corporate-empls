package com.diplom.webinar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diplom.webinar.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findByNameIgnoreCase(String name);  
}
