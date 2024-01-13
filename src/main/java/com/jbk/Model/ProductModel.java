package com.jbk.Model;

import com.jbk.entity.CategoryEntity;

public class ProductModel {

	private long productId;
	private String productName;
	private SupplierModel supplier;
	private CategoryEntity category;
	private int productQuntity;
	private double productPrice;
	private int deliveryCharges;

	public ProductModel() {

	}

	public ProductModel(long productId, String productName, SupplierModel supplier, CategoryEntity category,
			int productQuntity, double productPrice, int deliveryCharges) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.supplier = supplier;
		this.category = category;
		this.productQuntity = productQuntity;
		this.productPrice = productPrice;
		this.deliveryCharges = deliveryCharges;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public int getProductQuntity() {
		return productQuntity;
	}

	public void setProductQuntity(int productQuntity) {
		this.productQuntity = productQuntity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(int deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

}
