package com.solutions.denisovich.service.implementations;

import com.solutions.denisovich.config.MySpringMvcDispatcherServletInitializer;
import com.solutions.denisovich.config.SpringConfig;
import com.solutions.denisovich.model.Role;
import com.solutions.denisovich.service.RoleService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {SpringConfig.class, MySpringMvcDispatcherServletInitializer.class})
class RoleServiceImplTest {
    private static final Logger LOG = LogManager.getLogger(RoleServiceImplTest.class);
    @Autowired
    RoleService roleService;

    @Test
    void testShouldFindRoleByName() {
        Role actualRole = roleService.findByName("admin");
        Role expectedRole = new Role(1L, "admin");
        assertEquals(expectedRole, actualRole);
    }

    @Test
    void testShouldCreateRole() {
        Role role = new Role("superAdmin");
        roleService.create(role);
        List<Role> expectedList = List.of(new Role(1L, "admin"), new Role(2L, "user"),
                new Role(3L, "superAdmin"));
        List<Role> actualList = roleService.findAll();
        assertEquals(expectedList, actualList);
    }

    @Test
    void testShouldUpdateRole() {
        Role role = new Role("superAdmin");
        roleService.create(role);
        role.setName("mostSuperAdmin");
        roleService.update(role);
        List<Role> expectedList = List.of(new Role("admin"), new Role("user"),
                new Role("mostSuperAdmin"));
        List<Role> actualList = roleService.findAll();
        assertEquals(expectedList, actualList);
    }

    @Test
    void testShouldRemoveRole() {
        Role role = new Role("superAdmin");
        roleService.create(role);
        List<Role> expectedList = List.of(new Role("admin"), new Role("user"),
                new Role("superAdmin"));
        List<Role> actualList = roleService.findAll();
        assertEquals(expectedList, actualList);
        roleService.remove(role);
        List<Role> expectedListAfterRemoving = List.of(new Role( "admin"), new Role("user"));
        List<Role> actualListAfterRemoving = roleService.findAll();
        roleService.create(role);
        assertEquals(expectedListAfterRemoving, actualListAfterRemoving);
    }

    @Test
    void testShouldFindAllRoles() {
        List<Role> expectedList = roleService.findAll();
        List<Role> actualList = Arrays.asList(new Role("admin"), new Role("user"));
        assertEquals(expectedList, actualList);
    }

    @Test
    void testShouldFindRoleById() {
        Role expectedRole = roleService.findById(1L);
        Role actualRole = new Role("admin");
        assertEquals(expectedRole, actualRole);
    }
}