package com.jbk.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.SupplierEntity;
import com.jbk.exceptions.ResourceNotExist;
import com.jbk.exceptions.SomethingWentWrongException;

@Component
public class SupplierDaoImpl implements SupplierDao {

	@Autowired
	private SessionFactory sessionFactory;
	// create SessionFactory instance

	@Override
	public int addSupplier(SupplierEntity supplierEntity) {
		int status = 0;
		try {
			// open session from SessionFactory
			Session session = sessionFactory.openSession();
			// use methods of session to perform operation
			session.save(supplierEntity);
			session.beginTransaction().commit();
			status = 1;
		} catch (PersistenceException e) {
			status = 2;
		}

		catch (Exception e) {
			status = 3;
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public SupplierEntity getSupplierById(long supplierId) {
		SupplierEntity supplierEntity = null;

		try {
			Session session = sessionFactory.openSession();

			supplierEntity = session.get(SupplierEntity.class, supplierId);
		} catch (Exception e) {
			throw new SomethingWentWrongException("Something Went Wrong");

		}
		return supplierEntity;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<SupplierEntity> getAllSupplier() {
		List<SupplierEntity> list=null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(SupplierEntity.class);
			//criteria.setFirstResult(2);
			//criteria.setMaxResults(3);
			//criteria.add(Restrictions.eq("supplierName", "cc Distributor"));
			criteria.addOrder(Order.asc("supplierName"));
			list = criteria.list();  // from SupplierEntity //  select * from supplier

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something Went Wrong During Retrive All Supplier !");
		}
		return list;
	}

	@Override
	public void updateSupplier(SupplierEntity supplierEntity) {

		try {
			Session session = sessionFactory.openSession();
			session.update(supplierEntity);
			session.beginTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SomethingWentWrongException("Something Went Wrong While Updating");
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
			throw new SomethingWentWrongException("Something Went Wrong While Deleting");

		}
	}

}
