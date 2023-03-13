package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.UserDao;

import javax.ws.rs.*;

import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.UserDto;

import java.util.List;
import java.util.logging.Logger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/user")
@Tag(name = "User Resource", description = "API to manage users")
public class UserResource {

    private static final Logger logger = Logger.getLogger(UserResource.class.getName());

    UserDao daouser;

    public UserResource() {
        this.daouser = new UserDao();
    }

    @GET
    @Path("/test")
    @Operation(summary = "Test endpoint", description = "Endpoint to test the API")
    @ApiResponse(responseCode = "200", description = "🟩", content = @Content(mediaType = "text/html"))
    public String helloWorld() {
        return "hello";
    }

    @GET
    @Path("/{userId}")
    @Operation(summary = "Find user by ID", description = "Returns a user based on the given ID")
    @ApiResponse(responseCode = "200", description = "User found 🟩", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "404", description = "User not found 🟥")
    public UserDto findUserById(@PathParam("userId") Long id) {
        User u = daouser.findOne(id);
        return new UserDto(u.getName(), u.getEmail());
    }

    @GET
    @Path("/username/{name}")
    @Operation(summary = "Find user by username", description = "Returns a user based on the given username")
    @ApiResponse(responseCode = "200", description = "User found 🟩", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "404", description = "User not found 🟥")
    public User findByUserByUsername(@PathParam("name") String name) {
        return daouser.findByUsername(name);
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    @Operation(summary = "Get all users", description = "Returns a list of all the users")
    @ApiResponse(responseCode = "200", description = "Users found 🟩", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class, type = "array")))
    public List<User> getAllUser() {
        return this.daouser.findAll();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/add")
    @Operation(summary = "Add a new user", description = "Adds a new user to the database")
    @ApiResponse(responseCode = "200", description = "User added 🟩", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    public User addUser(UserDto user) {
        User u = new User();
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        return daouser.save(u);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/delete/{userId}")
    @Operation(summary = "Delete user by ID", description = "Deletes a single user by ID")
    @ApiResponse(responseCode = "200", description = "User deleted 🟩")
    @ApiResponse(responseCode = "404", description = "User not found 🟥")
    public void deleteUserById(@PathParam("userId") Long id) {
        daouser.deleteById(id);
    }

    @DELETE
    @Consumes("application/json")
    @Path("/delete")
    @Operation(summary = "Delete user", description = "User object that needs to be deleted")
    @ApiResponse(responseCode = "200", description = "User deleted 🟩")
    @ApiResponse(responseCode = "404", description = "User not found 🟥")
    public void deleteUser(User user) {
        daouser.delete(user);
    }

    @PUT
    @Consumes("application/json")
    @Path("/update")
    @Operation(summary = "Update an existing user")
    @ApiResponse(responseCode = "200", description = "User updated 🟦", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "User not found 🟥")
    public User updateUser(User user) {
        return daouser.update(user);
    }
}
