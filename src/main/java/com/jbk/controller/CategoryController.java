package com.jbk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.exceptions.ResourceAlreadyExistException;
import com.jbk.exceptions.ResourceNotExist;
import com.jbk.model.CategoryModel;
import com.jbk.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/add-category")
	public ResponseEntity<String> addCategory(@RequestBody @Valid CategoryModel categoryModel) {

		int status = categoryService.addCategory(categoryModel);

		if (status == 1) {
			return new ResponseEntity<String>("Category Added Successfully !!", HttpStatus.CREATED);
		} else if (status == 2) {
			throw new ResourceAlreadyExistException("Category Already Exists, Check Unique Fields");

		} else {
			return new ResponseEntity<String>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/get-category-by-id/{category-id}")
	public ResponseEntity<CategoryModel> getCategoryById(@PathVariable("category-id") long categoryId) {
		
		CategoryModel categoryModel = categoryService.getCategoryById(categoryId);
		if(categoryModel!=null) {
			return new ResponseEntity<CategoryModel>(categoryModel,HttpStatus.FOUND);
		}else {
			throw new ResourceNotExist("Category Not Found - Id = "+categoryId);
		}	
	}
	
	@PutMapping("/update-category")
	public ResponseEntity<String> updateCategory(@RequestBody CategoryModel categoryModel){
		
		categoryService.updateCategory(categoryModel);
		return new ResponseEntity<String> ("Category Updated Successfully !!",HttpStatus.OK);
		//return ResponseEntity.ok("Category Updated Successfully !!");
	}
	
	@DeleteMapping("/delete-category")
	public ResponseEntity<String> deleteCategory(@RequestParam long categoryId){
		
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<String> ("Category Deleted Successfully !!",HttpStatus.OK);
	
	}
	
	@GetMapping("/get-all-category")
	public ResponseEntity<List<CategoryModel>> getAllCategory(){
		
		List<CategoryModel> allCategorys = categoryService.getAllCategories();
		
		return new ResponseEntity<List<CategoryModel>>(allCategorys,HttpStatus.FOUND);
		
	}

}
