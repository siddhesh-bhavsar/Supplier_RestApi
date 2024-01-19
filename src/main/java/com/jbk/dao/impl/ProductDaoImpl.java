package com.jbk.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jbk.dao.ProductDao;
import com.jbk.entity.ProductEntity;
import com.jbk.exceptions.SomethingWentWrongException;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int addProduct(ProductEntity productEntity) {
		int status = 0;
		try {
			Session session = sessionFactory.openSession();
			session.save(productEntity);
			session.beginTransaction().commit();
			status = 1;
		} catch (RollbackException e) {
			e.printStackTrace();
			status = 3;
		}

		catch (PersistenceException e) {
			e.printStackTrace();
			status = 2;
		}

		catch (Exception e) {
			status = 3;
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public ProductEntity getProductById(long productId) {
		ProductEntity productEntity = null;
		try {
			Session session = sessionFactory.openSession();
			productEntity = session.get(ProductEntity.class, productId);
		} catch (Exception e) {
			throw new SomethingWentWrongException("Something Went Wrong");

		}
		return productEntity;
	}

	@Override
	public List<ProductEntity> getAllProducts() {
		List<ProductEntity> list = null;
		try {
			Session session = sessionFactory.openSession();
//			Criteria criteria = session.createCriteria(ProductEntity.class);
//			list = criteria.list();  // from ProductEntity 

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<ProductEntity> criteriaQuery = criteriaBuilder.createQuery(ProductEntity.class);
			criteriaQuery.from(ProductEntity.class);

			list = session.createQuery(criteriaQuery).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something Went Wrong During Retrive All Product !");
		}
		return list;
	}

	@Override
	public int updateProduct(ProductEntity productEntity) {
		int status = 0;
		try {
			Session session = sessionFactory.openSession();
			session.update(productEntity);
			session.beginTransaction().commit();
			status = 1;

		} catch (Exception e) {
			status = 3;
		}
		return status;
	}

	@Override
	public int deleteProduct(long productId) {
		int status = 0;
		try {
			Session session = sessionFactory.openSession();

			ProductEntity dbProduct = session.get(ProductEntity.class, productId);
			if (dbProduct != null) {
				session.delete(dbProduct);
				session.beginTransaction().commit();
				status = 1;
			} else {
				status = 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 3;
		}
		return status;
	}

	@Override
	public ProductEntity getProductByName(String productName) {
		ProductEntity productEntity = null;
		try {
			Session session = sessionFactory.openSession();
			// select * from product where productname=?
			// FROM ProductEntity WHERE productName= :pname
			Query query = session.createQuery("FROM ProductEntity WHERE productName= :pname");

			query.setParameter("pname", productName);

			productEntity = (ProductEntity) query.uniqueResult();

			// Criteria criteria = session.createCriteria(ProductEntity.class);

//			criteria.add(Restrictions.eq("productName", productName));
//			productEntity = (ProductEntity) criteria.uniqueResult();

//			CriteriaBuilder cb = session.getCriteriaBuilder();
//			CriteriaQuery<ProductEntity> query = cb.createQuery(ProductEntity.class);
//			Root<ProductEntity> root = query.from(ProductEntity.class);
//
//			query.select(root).where(cb.equal(root.get("productName"), productName));
//
//			productEntity = session.createQuery(query).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while retrive product");
		}
		return productEntity;
	}

	@Override
	public List<ProductEntity> sortProductByName() {
		List<ProductEntity> list = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(ProductEntity.class);
			criteria.addOrder(Order.desc("productName"));
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public double getMaxPrice() {
		double maxPrice = 0;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(ProductEntity.class);

			criteria.setProjection(Projections.max("productPrice"));

			List<Double> list = criteria.list();

			if (!list.isEmpty()) {

				maxPrice = list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxPrice;
	}

	@Override
	public List<ProductEntity> getMaxPriceProduct() {
		List<ProductEntity> list = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(ProductEntity.class);

			double maxPrice = getMaxPrice();

			criteria.add(Restrictions.eq("productPrice", maxPrice));

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
