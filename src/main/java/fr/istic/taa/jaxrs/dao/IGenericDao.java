package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.domain.User;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<K, T> {
 
   T findOne(final K id);
 
   List<T> findAll();
  
   T save(final T entity);
 
   T update(final T entity);
 
   void delete(final T entity);
 
   void deleteById(final K entityId);
}