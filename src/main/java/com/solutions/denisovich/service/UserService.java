package com.solutions.denisovich.service;

import com.solutions.denisovich.model.User;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Service
public interface UserService extends EntityService<User> {

    boolean create(User user);

    @PUT
    @Path("/users/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    void update(User user);


    void remove(User entity);
    List<User> findAll();
    User findByLogin(String login);
    User findByEmail(String email);
    Map<User, Integer> getUsersWithAge(List<User> users);
    boolean isLoginAlreadyExists(String username);
}
