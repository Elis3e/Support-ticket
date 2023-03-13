package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.TagDao;
import fr.istic.taa.jaxrs.dao.TicketDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.Comment;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.CommentDto;
import fr.istic.taa.jaxrs.dto.TagDto;
import fr.istic.taa.jaxrs.dto.TicketDto;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;



@Path("/ticket")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Ticket Resource", description = "API to manage tickets")
public class TicketResource {

    private static final Logger logger = Logger.getLogger(UserResource.class.getName());

    TicketDao daoticket;
    UserDao daouser;
    TagDao daotag;

    public TicketResource() {
        this.daoticket = new TicketDao();
        this.daouser = new UserDao();
        this.daotag = new TagDao();
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Ticket> getAllTicket() {
        return this.daoticket.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/add")
    public String addTicket(TicketDto ticket) {

        String title = ticket.getTitle();
        String body = ticket.getBody();
        User user = daouser.findOne(ticket.getCreatorId());

        List<Tag> tags = new ArrayList<>();
        for (Long tagId : ticket.getTagsId()) {
            Tag tag = daotag.findOne(tagId);
            tags.add(tag);
        }

        Ticket t = new Ticket(ticket.getTitle(), ticket.getBody(), null, user, tags);
        daoticket.save(t);
        return "ticket " + t.getTitle() + " added ✅";
    }

    @DELETE
    @Consumes("application/json")
    @Produces("text/html")
    @Path("/delete/{ticketId}")
    public String deleteTicketById(@PathParam("ticketId") Long id) {
        daoticket.deleteById(id);
        return "ticket " + id + " deleted ❎";
    }

    @PUT
    @Consumes("application/json")
    @Produces("text/html")
    @Path("/update")
    public String updateTicket(Ticket ticket) {
        daoticket.update(ticket);
        return "ticket " + ticket.getId() + " updated 🔄️";
    }
}
