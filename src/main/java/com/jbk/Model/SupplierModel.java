package com.jbk.Model;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;





public class SupplierModel {

	@Min(1)
	private long supplierId;
	
	@NotBlank(message = "Invalid Supplier Name")
//	@NotNull(message = "Supplier Name Is Required")
	@Pattern(regexp = "^[a-zA-Z ]{3,50}$", message = "Supplier name should consist of letters, and space, and be between 3 and 50 characters long.")
	private String supplierName;
	@NotBlank(message = "Invalid City Name")
	@Pattern(regexp = "^[a-zA-Z]{4,50}$", message = "Supplier city should consist of letters, and space, and be between 4 and 50 characters long.")
	private String city;
	@Min(100000)
	@Max(999999)
	private int postalCode;
	@NotBlank(message = "Invalid Country Name")
	@Pattern(regexp = "^[a-zA-Z]{4,50}$", message = "Country name should consist of letters, and space, and be between 4 and 50 characters long.")
	private String country;
	@NotBlank(message = "Invalid Mobile No")
	@Pattern(regexp = "^[1-9][0-9]{9}$", message = "Supplier mobile no should be only 2 numeric digit.")
	private String mobileNo;

	public SupplierModel() {

	}

	public SupplierModel(long supplierId, String supplierName, String city, int postalCode, String country,
			String mobileNo) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
		this.mobileNo = mobileNo;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
