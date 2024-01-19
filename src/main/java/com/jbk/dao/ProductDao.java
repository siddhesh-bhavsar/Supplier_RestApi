package com.jbk.dao;

import java.util.List;

import com.jbk.entity.ProductEntity;

public interface ProductDao {
	public int addProduct(ProductEntity productEntity);
	public ProductEntity getProductById(long productId);
	public List<ProductEntity> getAllProducts();
	public int updateProduct(ProductEntity productEntity);
	public int deleteProduct(long productId);
	
	public ProductEntity getProductByName(String productName);
	
	public List<ProductEntity> sortProductByName();
	public double getMaxPrice();
	public List<ProductEntity> getMaxPriceProduct();
}
