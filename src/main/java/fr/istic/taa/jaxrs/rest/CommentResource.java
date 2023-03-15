package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.CommentDao;
import fr.istic.taa.jaxrs.dao.TicketDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.Comment;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.CommentDto;
import fr.istic.taa.jaxrs.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Path("/all/{ticketID}")
    @Operation(summary = "Get all comments by ticket", description = "Returns a list of all the comments based on the given ticket")
    @ApiResponse(responseCode = "200", description = "Users found 🟩", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class, type = "array")))
    public List<Comment> getAllCommentsByTicket(@PathParam("ticketID") Long ticketID) {
        Ticket ticket = daoticket.findOne(ticketID);
        return this.daocomment.findAllByTicket(ticket);
    }

    @POST
    @Consumes("application/json")
    @Path("/add")
    @Operation(summary = "Add a new user comment", description = "Adds a user comment to a ticket in the database")
    @ApiResponse(responseCode = "200", description = "Comment added 🟩", content = @Content(mediaType = "application/json"))
    public Comment addComment(@Parameter(description = "Comment that needs to be added to a ticket") CommentDto com) {
        User user = daouser.findOne(com.getUserId());
        Ticket ticket = daoticket.findOne(com.getTicketId());
        String text = com.getText();
        Comment c = new Comment(text, user, ticket);
        return daocomment.save(c);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/delete/{commentID}")
    @Operation(summary = "Delete user comment by ID", description = "Deletes a user comment based on the given ID")
    @ApiResponse(responseCode = "200", description = "comment deleted 🟩")
    @ApiResponse(responseCode = "404", description = "comment not found 🟥")
    public void deleteCommentById(@PathParam("commentID") Long commentID) {
        daocomment.deleteById(commentID);
    }
}
