package com.jbk.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.CategoryDao;
import com.jbk.entity.CategoryEntity;
import com.jbk.exceptions.SomethingWentWrongException;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public int addCategory(CategoryEntity categoryEntity) {
		int status = 0;
		try {
			Session session = sessionFactory.openSession();
			session.save(categoryEntity);
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
	public CategoryEntity getCategoryById(long categoryId) {
		CategoryEntity categoryEntity = null;
		try {
			Session session = sessionFactory.openSession();
			categoryEntity = session.get(CategoryEntity.class, categoryId);
		} catch (Exception e) {
			throw new SomethingWentWrongException("Something Went Wrong");

		}
		return categoryEntity;
	}

	@Override
	public List<CategoryEntity> getAllCategories() {
		List<CategoryEntity> list=null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(CategoryEntity.class);
			list = criteria.list();  // from CategoryEntity 

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something Went Wrong During Retrive All Category !");
		}
		return list;
	}

	@Override
	public int updateCategory(CategoryEntity categoryEntity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCategory(long categoryId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
