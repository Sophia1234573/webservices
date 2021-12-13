package com.solutions.denisovich.service;

import com.solutions.denisovich.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService extends EntityService<Role> {
    boolean create(Role role);
    void update(Role role);
    void remove(Role role);
    Role findByName(String name);
}
