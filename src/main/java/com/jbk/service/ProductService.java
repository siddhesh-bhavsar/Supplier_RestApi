package com.jbk.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.model.ProductModel;

public interface ProductService {
	public int addProduct(ProductModel productModel);
	public ProductModel getProductById(long productId);
	public List<ProductModel> getAllProducts();
	public int updateProduct(ProductModel productModel);
	public int deleteProduct(long productId);
	
	public ProductModel getProductByName(String productName);
	
	public List<ProductModel> sortProductByName();
	public double getMaxPrice();
	public List<ProductModel> getMaxPriceProduct();
	
	public String uploadSheet(MultipartFile file);
}
