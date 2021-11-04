package com.dmurealfinal.vicmag.domain.entity.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("SELECT c.category FROM Category AS c")
    List<String> getCategoryList();
}