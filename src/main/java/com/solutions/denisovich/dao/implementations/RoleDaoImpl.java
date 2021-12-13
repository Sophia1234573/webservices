package com.solutions.denisovich.dao.implementations;

import com.solutions.denisovich.dao.RoleDao;
import com.solutions.denisovich.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    private static final Logger LOG = LogManager.getLogger(RoleDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role findByName(String name) {
        Role role = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<Role> query = session.createNamedQuery("role_FindByName",
                    Role.class);
            query.setParameter("roleName", name);
            role = query.getSingleResult();
        } catch (NoResultException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return role;
    }

    @Override
    public void create(Role entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
    }

    @Override
    public void update(Role entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);

    }

    @Override
    public void remove(Role entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete("role", entity);
        session.flush();
    }

    @Override
    public List<Role> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Role>) session.createQuery("from Role").list();
    }

    @Override
    public Role findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }
}
