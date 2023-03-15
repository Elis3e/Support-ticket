package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.TagDao;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.TagDto;
import fr.istic.taa.jaxrs.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.*;
import java.util.List;

@Path("/tag")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag Resource", description = "API to manage tags")
public class TagResource {

    private TagDao daotag;

    public TagResource() {
        this.daotag = new TagDao();
    }

    @GET
    @Path("/all")
    @Operation(summary = "Get all tags", description = "Returns a list of all the tags")
    @ApiResponse(responseCode = "200", description = "Tags found 🟩", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tag.class, type = "array")))
    public List<Tag> getAllTags() {
        return this.daotag.findAll();
    }

    @POST
    @Consumes("application/json")
    @Path("/add")
    @Operation(summary = "Add a new tag", description = "Adds a new tag to the database")
    @ApiResponse(responseCode = "200", description = "Tag added 🟩", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tag.class)))
    public Tag addTag(TagDto tag) {
        Tag t = new Tag(tag.getLabel());
        return daotag.save(t);
    }

    @GET
    @Path("/{tagID}")
    @Operation(summary = "Find tag by ID", description = "Returns a tag based on the given ID")
    @ApiResponse(responseCode = "200", description = "Tag found 🟩", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Tag not found 🟥")
    public TagDto findTagById(@PathParam("tagID") Long tagID) {
        Tag t = daotag.findOne(tagID);
        String label = t.getLabel();
        return new TagDto(label);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/delete/{tagID}")
    @Operation(summary = "Delete tag by ID", description = "Deletes a tag based on the given ID")
    @ApiResponse(responseCode = "200", description = "Tag deleted 🟩")
    @ApiResponse(responseCode = "404", description = "Tag not found 🟥")
    public void deleteTagById(@PathParam("tagID") Long tagID) {
        daotag.deleteById(tagID);
    }
}
