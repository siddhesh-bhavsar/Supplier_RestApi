package com.jbk.Dao.Impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.Dao.SupplierDao;
import com.jbk.Exception.ResourceNotExist;
import com.jbk.Exception.SomethingWentWrong;
import com.jbk.entity.SupplierEntity;



@Component
public class SupplierDaoImpl implements SupplierDao {

	//create sessionfactory instance 
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public int addSupplier(SupplierEntity supplierEntity) {
		int status = 0;
		try {
			//open session from sessionfactory
			Session session = sessionFactory.openSession();
			//use methods of session to perform operation 
			session.save(supplierEntity);
			session.beginTransaction().commit();
			status = 1;
			
		}catch (PersistenceException e) {
			status = 2;
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrong("Something Went Wrong");
			
		}
		
		
		
		return status;
	}
	@Override
	public SupplierEntity getSupplierById(long supplierId) throws SomethingWentWrong  {
		
		SupplierEntity supplierEntity = null;

		try {
			Session session = sessionFactory.openSession();
			supplierEntity = session.get(SupplierEntity.class, supplierId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return supplierEntity;
	}
	@Override
	public int updateSupplier(SupplierEntity supplierEntity) {
	
		
		try {
			Session session = sessionFactory.openSession();
			session.update(supplierEntity);
			session.beginTransaction().commit();
			return 1;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SomethingWentWrong("Something Went Wrong While Updating");
		}
		
		 
	}
	@Override
	public void deleteSupplier(long supplierId) {
		try {
			Session session = sessionFactory.openSession();

			SupplierEntity dbSupplier = session.get(SupplierEntity.class, supplierId);
			if (dbSupplier != null) {
				session.delete(dbSupplier);
				session.beginTransaction().commit();
			} else {
				throw new ResourceNotExist("Supplier Not Exists to Delete Data -  ID = " + supplierId);
			}

		} catch (RollbackException e) {
			e.printStackTrace();
			throw new SomethingWentWrong("Something Went Wrong While Deleting");

		}
	}
	@SuppressWarnings("deprecation")
	@Override
	public List<SupplierEntity> getAllSupplier() {
		List<SupplierEntity> list=null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(SupplierEntity.class);
			criteria.addOrder(Order.asc("supplierName"));
			list = criteria.list();  

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrong("Something Went Wrong During Retrive All Supplier !");
		}
		return list;
	}

}
