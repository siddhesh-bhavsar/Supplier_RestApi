package com.jbk.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.Dao.SupplierDao;
import com.jbk.Exception.ResourceNotExist;
import com.jbk.Model.SupplierModel;
import com.jbk.Service.SupplierService;
import com.jbk.entity.SupplierEntity;

@Component
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private SupplierDao supplierDao;

	@Override
	public int addSupplier(SupplierModel supplierModel) {

		return supplierDao.addSupplier(mapper.map(supplierModel, SupplierEntity.class));
	}

	@Override
	public SupplierModel getSupplierById(long supplierId) {

		SupplierEntity supplierEntity = supplierDao.getSupplierById(supplierId);
		SupplierModel supplierModel = null;

		if (supplierEntity != null) {

			supplierModel = mapper.map(supplierEntity, SupplierModel.class);
		}

		return supplierModel;
	}

	@Override
	public int updateSupplier(SupplierModel supplierModel) {

		SupplierModel dbSupplierModel = getSupplierById(supplierModel.getSupplierId());

		if (dbSupplierModel != null) {

			return supplierDao.updateSupplier(mapper.map(supplierModel, SupplierEntity.class));

		} else {

			throw new ResourceNotExist("Supplier Not Exist To Update Data Id = " + supplierModel.getSupplierId());
		}

	}

	@Override
	public void deleteSupplier(long supplierId) {

		supplierDao.deleteSupplier(supplierId);

	}

	@Override
	public List<SupplierModel> getAllSuppliers() {
		List<SupplierEntity> allSupplier = supplierDao.getAllSupplier();
		List<SupplierModel> list = null;
		if (!allSupplier.isEmpty()) {

			list = allSupplier.stream().map(entity -> mapper.map(entity, SupplierModel.class))
					.collect(Collectors.toList());

		} else {
			throw new ResourceNotExist("Supplier Not Exists");
		}

//		
		return list;

	}
}
