package com.solutions.denisovich.service.implementations;

import com.solutions.denisovich.dao.RoleDao;
import com.solutions.denisovich.model.Role;
import com.solutions.denisovich.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public boolean create(Role role) {
        Role roleFromDB = roleDao.findByName(role.getName());
        if (roleFromDB != null) {
            return false;
        }
        roleDao.create(role);
        return true;
    }

    @Override
    @Transactional
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    @Transactional
    public void remove(Role role) {
        roleDao.remove(role);
    }

    @Override
    @Transactional
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    @Transactional
    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    @Transactional
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
