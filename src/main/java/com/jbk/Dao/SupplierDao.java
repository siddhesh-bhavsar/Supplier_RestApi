package com.jbk.Dao;


import java.util.List;

import com.jbk.entity.SupplierEntity;

public interface SupplierDao {

	public int addSupplier(SupplierEntity supplierEntity);
	public SupplierEntity getSupplierById(long supplierId);
	public int updateSupplier(SupplierEntity supplierEntity);
	public void deleteSupplier(long supplierId);
	public List<SupplierEntity> getAllSupplier();
}
