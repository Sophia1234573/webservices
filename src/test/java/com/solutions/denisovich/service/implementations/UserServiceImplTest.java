package com.solutions.denisovich.service.implementations;

import com.solutions.denisovich.config.MySpringMvcDispatcherServletInitializer;
import com.solutions.denisovich.config.SpringConfig;
import com.solutions.denisovich.model.Role;
import com.solutions.denisovich.model.User;
import com.solutions.denisovich.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {SpringConfig.class, MySpringMvcDispatcherServletInitializer.class})
class UserServiceImplTest {
    private static final Logger LOG = LogManager.getLogger(UserServiceImplTest.class);
    @Autowired
    private UserService userService;

    @Test
    void testShouldFindAllUsers() {

        List<User> expectedList = userService.findAll();
        List<User> actualList = List.of(getUserToTest(),
                new User("user", "user", "olesya@gmail.com",
                        "Olesya", "Petrova", convertToBirthday("04/01/1987"),
                        new Role("user")));
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void testShouldFindUserByLogin() {
        User expectedUser = userService.findByLogin("admin");
        User actualUser = getUserToTest();
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testShouldFindUserByEmail() {
        User expectedUser = userService.findByEmail("sophia@gmail.com");
        User actualUser = getUserToTest();
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testShouldCreateUser() {

        User userToSave = new User("login", "password", "alisa@gmail.com",
                "Alisa", "Govtva", convertToBirthday("30/06/2016"), new Role("admin"));
        userService.create(userToSave);
        List<User> expectedList = userService.findAll();
        List<User> actualList = List.of(getUserToTest(),
                new User("user", "user", "olesya@gmail.com",
                        "Olesya", "Petrova", convertToBirthday("04/01/1987"),
                        new Role("user")), userToSave);
        assertEquals(expectedList, actualList);
    }

    @Test
    void testShouldUpdateUser() {

        User userToUpdate = new User(3L, "login", "password", "alisa@gmail.com",
                "Alisa", "Govtva", convertToBirthday("30/06/2016"), new Role(1L, "admin"));

        userService.create(userToUpdate);
        userToUpdate.setFirstName("Alisochka");
        userService.update(userToUpdate);
        User userByLogin = userService.findByLogin("login");
        assertEquals("Alisochka", userByLogin.getFirstName());
    }

    @Test
    void testShouldRemoveUser() {
        User userToDelete = userService.findByLogin("user");
        userService.remove(userToDelete);
        List<User> actualList = List.of(getUserToTest());

        List<User> expectedList = userService.findAll();
        userService.create(userToDelete);
        assertEquals(expectedList, actualList);
    }

    @Test
    void testShouldFindUserById() {
        User expectedUser = userService.findById(1L);
        User actualUser = getUserToTest();
        assertEquals(expectedUser, actualUser);
    }

    private User getUserToTest() {

        return new User("admin", "admin", "sophia@gmail.com",
                "Sophia", "Denisovich", convertToBirthday("28/05/1987"), new Role("admin"));
    }

    private Timestamp convertToBirthday(String birthdayDate) {

        Date birthday = new Date();
        try {
            birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthdayDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return new Timestamp(birthday.getTime());
    }
}