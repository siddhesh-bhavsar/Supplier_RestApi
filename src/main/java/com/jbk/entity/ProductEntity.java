package com.jbk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table (name = "product")
public class ProductEntity {

	@Id
	@Column (name = "product_id",unique = true,nullable = false)
	private long productId;
	@Column (name = "product_name",unique = true,nullable = false)
	private String productName;
	
	@OneToOne
	private SupplierEntity supplier;
	
	@OneToOne
	private CategoryEntity category;
	
	@Column (name = "product_qty",nullable = false)
	private int productQuntity;
	
	@Column (name = "product_price",nullable = false)
	private double productPrice;
	
	@Column (name = "deliverycharges",nullable = false)
	private int deliveryCharges;

	public ProductEntity() {

	}

	public ProductEntity(long productId, String productName, SupplierEntity supplier, CategoryEntity category,
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

	public SupplierEntity getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierEntity supplier) {
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
