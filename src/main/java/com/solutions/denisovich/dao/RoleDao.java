package com.solutions.denisovich.dao;

import com.solutions.denisovich.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends Dao<Role> {
    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String name);
}
