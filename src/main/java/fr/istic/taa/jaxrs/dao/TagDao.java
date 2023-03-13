package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.domain.*;

import javax.persistence.TypedQuery;
import java.util.List;

public class TagDao extends AbstractJpaDao<Long, Tag> {

    public TagDao() {
        setClazz(Tag.class);
    }

    public List<Tag> findAllByTicket(Ticket ticket) {
        TypedQuery<Tag> query = entityManager.createQuery(
                "SELECT tag FROM Tag tag JOIN tag.tickets ticket WHERE ticket = :ticket", Tag.class);
        query.setParameter("ticket", ticket);
        return query.getResultList();
    }
}

