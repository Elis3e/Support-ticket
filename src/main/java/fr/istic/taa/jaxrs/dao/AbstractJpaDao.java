package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.domain.User;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class AbstractJpaDao<K extends Serializable, T> implements IGenericDao<K, T> {

	private Class<T> clazz;

	protected EntityManager entityManager;

	public AbstractJpaDao() {
		this.entityManager = EntityManagerHelper.getEntityManager();
	}

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(K id) {
		return entityManager.find(clazz, id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("select e from " + clazz.getName() + " as e",clazz).getResultList();
	}

	public T save(T entity) {
		EntityTransaction t = entityManager.getTransaction();
		t.begin();
		entityManager.merge(entity);;
		t.commit();
		return entity;
	}

	public T update(final T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		T res = entityManager.merge(entity);
		t.commit();
		return res;
	}

	public void delete(T entity) {
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		entityManager.remove(entity);
		t.commit();

	}

	public void deleteById(K entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
}
