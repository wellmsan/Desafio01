package br.ufba.fpc.desafio01.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Example;

public abstract class DaoAbstract<T> {
	
	protected Class<T> clazz;
	protected EntityManager entityManager;
	
	public void setClazz (Class<T> clazz){
		this.clazz = clazz;
	}
	
	protected abstract EntityManager getEntityManager();
	
	public T getById(Long id) {
		return (T) getEntityManager().find(this.clazz, id);
	}

	public List<T> getAll() {
		return getEntityManager().createQuery("FROM " + this.clazz.getName()).getResultList();
	}
	
	public List<T> getAllBy(T t) {
		Session session = (Session) getEntityManager().getDelegate();
		Example example = Example.create(t).excludeZeroes();		
		return session.createCriteria(this.clazz).add(example).list();
	}

	public void save(T t) {
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(t);
			getEntityManager().getTransaction().commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			getEntityManager().getTransaction().rollback();
		}

	}
	
	public void update(T t) {
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().merge(t);
			getEntityManager().getTransaction().commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			getEntityManager().getTransaction().rollback();
		}

	}

	public void remove(T t) {
		try {
			getEntityManager().getTransaction().begin();
			ModelBase base = (ModelBase) t;
			t = (T) getEntityManager().find(this.clazz, base.getId());
			getEntityManager().remove(t);
			getEntityManager().getTransaction().commit();
		} catch (HibernateException ex) {
			ex.printStackTrace();
			getEntityManager().getTransaction().rollback();
		}
	}


}
