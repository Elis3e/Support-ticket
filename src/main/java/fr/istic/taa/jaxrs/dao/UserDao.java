package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.domain.*;

import javax.persistence.TypedQuery;

public class UserDao extends AbstractJpaDao<Long, User> {

    public UserDao() {
        setClazz(User.class);
    }

    public User findByUsername(String name) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.name = :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}

