package com.example.tabletguides.repos;

import com.example.tabletguides.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
}