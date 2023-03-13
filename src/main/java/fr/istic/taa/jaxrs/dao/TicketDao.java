package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.domain.*;

import java.util.List;
import javax.persistence.TypedQuery;

public class TicketDao extends AbstractJpaDao<Long, Ticket> {

    public TicketDao() {
        setClazz(Ticket.class);
    }

    public List<Ticket> findAllByUser(User user) {
        TypedQuery<Ticket> query = entityManager.createQuery(
                "SELECT t FROM Ticket t WHERE t.creator = :user", Ticket.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public List<Ticket> findAllByTag(Tag tag) {
        TypedQuery<Ticket> query = entityManager.createQuery(
                "SELECT t FROM Ticket t JOIN t.tags tag WHERE tag = :tag", Ticket.class);
        query.setParameter("tag", tag);
        return query.getResultList();
    }
}
