package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.domain.*;

import java.util.List;
import javax.persistence.TypedQuery;

public class CommentDao extends AbstractJpaDao<Long, Comment> {

    public CommentDao() {
        setClazz(Comment.class);
    }

    public List<Comment> findAllByTicket(Ticket ticket) {
        TypedQuery<Comment> query = entityManager.createQuery(
                "SELECT c FROM Comment c WHERE c.ticket = :ticket", Comment.class);
        query.setParameter("ticket", ticket);
        return query.getResultList();
    }
}
