package com.jbk.dao;

import java.util.List;

import com.jbk.entity.SupplierEntity;

public interface SupplierDao {
	public int addSupplier(SupplierEntity supplierEntity);
	public SupplierEntity getSupplierById(long supplierId);
	public List<SupplierEntity> getAllSupplier();
	public void updateSupplier(SupplierEntity supplierEntity);
	public void deleteSupplier(long supplierId);
}
