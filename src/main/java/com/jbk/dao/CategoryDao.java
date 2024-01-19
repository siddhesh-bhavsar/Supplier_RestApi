package com.jbk.dao;

import java.util.List;

import com.jbk.entity.CategoryEntity;

public interface CategoryDao {

	public int addCategory(CategoryEntity categoryEntity);
	public CategoryEntity getCategoryById(long categoryId);
	public List<CategoryEntity> getAllCategories();
	public int updateCategory(CategoryEntity categoryEntity);
	public int deleteCategory(long categoryId);
}
