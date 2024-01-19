package com.jbk.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.SupplierEntity;
import com.jbk.exceptions.ResourceNotExist;
import com.jbk.model.SupplierModel;
import com.jbk.service.SupplierService;

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
	public SupplierModel getSupplierById(long supplierId)  {
		
		
		SupplierEntity supplierEntity = supplierDao.getSupplierById(supplierId);
		
		System.out.println("in service");
		SupplierModel supplierModel =null;
		
		if(supplierEntity!=null) {
			 supplierModel=mapper.map(supplierEntity, SupplierModel.class);
		}
			return supplierModel;
		}

	@Override
	public List<SupplierModel> getAllSuppliers() {
		List<SupplierEntity> allSupplier = supplierDao.getAllSupplier();
		List<SupplierModel> list=null;
		if(!allSupplier.isEmpty()) {
			
		 list = allSupplier.stream().map(entity -> mapper.map(entity, SupplierModel.class)).collect(Collectors.toList());
			
		}else {
			throw new ResourceNotExist("Supplier Not Exists");
		}
		
		
		
//		List<SupplierModel> list=null;
//		if(!allSupplier.isEmpty()) {
//			list=new ArrayList<SupplierModel>();
//			
//			for (SupplierEntity supplierEntity : allSupplier) {
//				
//				SupplierModel supplierModel = mapper.map(supplierEntity, SupplierModel.class);
//				list.add(supplierModel);
//			}
//			
//		}
//		return list;
		return list;
		
	}

	@Override
	public void updateSupplier(SupplierModel supplierModel) {
		
		SupplierModel dbSupplierModel = getSupplierById(supplierModel.getSupplierId());
		
		
		if(dbSupplierModel!=null) {
			
		supplierDao.updateSupplier(mapper.map(supplierModel, SupplierEntity.class));
			
		}else {
			throw new ResourceNotExist("Supplier Not Exists to Update Data ID = "+ supplierModel.getSupplierId());
		}
		
	}

	@Override
	public void deleteSupplier(long supplierId) {
			
		supplierDao.deleteSupplier(supplierId);
		
	}

}
