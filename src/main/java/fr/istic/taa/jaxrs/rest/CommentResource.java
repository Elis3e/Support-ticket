package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.CommentDao;
import fr.istic.taa.jaxrs.dao.TicketDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.Comment;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.CommentDto;
import fr.istic.taa.jaxrs.dto.UserDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import javax.ws.rs.*;

@Path("/comment")
@Tag(name = "Comment Resource", description = "API to manage comments")
public class CommentResource {

    private CommentDao daocomment;
    private TicketDao daoticket;
    private UserDao daouser;

    public CommentResource() {
        this.daocomment = new CommentDao();
        this.daoticket = new TicketDao();
        this.daouser = new UserDao();
    }

    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Comment> getAllComments() {
        return this.daocomment.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/add")
    public String addComment(@Parameter(description = "Comment that needs to be added to a ticket") CommentDto com) {
        User user = daouser.findOne(com.getUserId());
        Ticket ticket = daoticket.findOne(com.getTicketId());
        Comment c = new Comment(com.getText(), user, ticket);
        daocomment.save(c);
        return "comment " + c.getText() + " added ✅";
    }

    @DELETE
    @Consumes("application/json")
    @Produces("text/html")
    @Path("/delete/{commentId}")
    public String deleteCommentById(@PathParam("commentId") Long id) {
        daocomment.deleteById(id);
        return "comment " + id + " deleted ❎";
    }
}
