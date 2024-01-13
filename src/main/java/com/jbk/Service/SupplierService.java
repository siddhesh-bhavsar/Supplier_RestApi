package com.jbk.Service;

import java.util.List;

import com.jbk.Model.SupplierModel;

public interface SupplierService {

	public int addSupplier (SupplierModel supplierModel);
	public SupplierModel getSupplierById(long supplierId);
	public int updateSupplier(SupplierModel supplierModel);
	public void deleteSupplier(long supplierId);
	public List<SupplierModel> getAllSuppliers();
	
}
