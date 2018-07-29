package com.example.tabletguides.repos;

import com.example.tabletguides.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepo extends CrudRepository<Category, Long> {

    @Query("SELECT id from Category where name=:name")
    Integer find(@Param("name") String name);
}