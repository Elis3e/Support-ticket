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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
    @Path("/{ticketID}")
    @Operation(summary = "Find ticket by ID", description = "Returns a ticket based on the given ID")
    @ApiResponse(responseCode = "200", description = "Tag found 🟩", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Tag not found 🟥")
    public Ticket findTagById(@PathParam("ticketID") Long ticketID) {
        return daoticket.findOne(ticketID);
    }

    @GET
    @Path("/all")
    @Operation(summary = "Get all tickets", description = "Returns a list of all the tickets")
    @ApiResponse(responseCode = "200", description = "Tickets found 🟩", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class, type = "array")))
    public List<Ticket> getAllTicket() {
        return this.daoticket.findAll();
    }

    @POST
    @Consumes("application/json")
    @Path("/add")
    @Operation(summary = "Create a new ticket", description = "Create a new ticket in the database")
    @ApiResponse(responseCode = "200", description = "Ticket added 🟩", content = @Content(mediaType = "application/json"))
    public Ticket addTicket(TicketDto ticket) {

        String title = ticket.getTitle();
        String body = ticket.getBody();
        User user = daouser.findOne(ticket.getCreatorId());

        List<Tag> tags = ticket.getTags();

        Ticket t = new Ticket(ticket.getTitle(), ticket.getBody(), null, user, tags);
        return daoticket.save(t);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/delete/{ticketID}")
    @Operation(summary = "Delete ticket by ID", description = "Deletes a ticket based on the given ID")
    @ApiResponse(responseCode = "200", description = "Ticket deleted 🟩")
    @ApiResponse(responseCode = "404", description = "Ticket not found 🟥")
    public void deleteTicketById(@PathParam("ticketID") Long id) {
        daoticket.deleteById(id);
    }

    @PUT
    @Consumes("application/json")
    @Path("/update")
    @Operation(summary = "Update an existing ticket", description = "Update an existing ticket in the database")
    @ApiResponse(responseCode = "200", description = "Ticket updated 🟦", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Ticket not found 🟥")
    public Ticket updateTicket(Ticket ticket) {
        return daoticket.update(ticket);
    }
}
