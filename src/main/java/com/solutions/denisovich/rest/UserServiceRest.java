package com.solutions.denisovich.rest;

import com.solutions.denisovich.model.User;
import com.solutions.denisovich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UserServiceRest {
    private final UserService userService;

    @Autowired
    public UserServiceRest(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/users/")
    public User create(User user) {
        userService.create(user);
        return user;
    }

    @PUT
    @Path("/users/{id}/")
    public Response update(@PathParam("id") String id, User user) {
        long idNumber = Long.parseLong(id);
        User existingUser = userService.findById(idNumber);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (existingUser.equals(user)) {
            return Response.notModified().build();
        }
        userService.update(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("users/{id}/")
    public void remove(@PathParam("id") String id) {
        long idNumber = Long.parseLong(id);
        User userById = userService.findById(idNumber);
        userService.remove(userById);
    }

    @GET
    @Path("/users/")
    List<User> findAll() {
        return userService.findAll();
    }

    @GET
    @Path("/users/{id}/")
    User findById(@PathParam("id") String id) {
        long idNumber = Long.parseLong(id);
        return userService.findById(idNumber);
    }

    @GET
    @Path("/users/{login}/")
    User findByLogin(@PathParam("login") String login) {
        return userService.findByLogin(login);
    }

    @GET
    @Path("/users/{email}/")
    User findByEmail(@PathParam("email") String email) {
        return userService.findByEmail(email);
    }
}
