package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.TagDao;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.dto.TagDto;

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
    @Produces("application/json")
    public List<Tag> getAllTags() {
        return this.daotag.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/add")
    public String addTag(TagDto tag) {
        Tag t = new Tag(tag.getLabel());
        daotag.save(t);
        return "tag " + tag.getLabel() + " added ✅";
    }

    @GET
    @Produces("application/json")
    @Path("/{tagId}")
    public TagDto findTagById(@PathParam("tagId") Long id) {
        Tag t = daotag.findOne(id);
        return new TagDto(t.getLabel());
    }

    @DELETE
    @Consumes("application/json")
    @Produces("text/html")
    @Path("/delete/{tagId}")
    public String deleteTagById(@PathParam("tagId") Long id) {
        daotag.deleteById(id);
        return "tag " + id + " deleted ❎";
    }
}
