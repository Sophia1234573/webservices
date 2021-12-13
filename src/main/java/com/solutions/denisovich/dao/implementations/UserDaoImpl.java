package com.solutions.denisovich.dao.implementations;

import com.solutions.denisovich.dao.RoleDao;
import com.solutions.denisovich.dao.UserDao;
import com.solutions.denisovich.model.Role;
import com.solutions.denisovich.model.User;
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
public class UserDaoImpl implements UserDao {
    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);
    @Autowired
    private RoleDao roleDao;
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<User> query = session.createNamedQuery("person_FindByLogin", User.class);
            query.setParameter("personLogin", login);
            user = query.getSingleResult();
        } catch (NoResultException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return user;
    }


    @Override
    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createNamedQuery("person_FindByEmail", User.class);
        query.setParameter("personEmail", email);
        return query.getSingleResult();
    }

    @Override
    public void create(User entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(getUserWithUpdatedRole(entity));
    }

    @Override
    public void update(User entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(getUserWithUpdatedRole(entity));
    }

    private User getUserWithUpdatedRole(User entity) {
        Role role = roleDao.findByName(entity.getRole().getName());
        entity.setRole(role);
        return entity;
    }

    @Override
    public void remove(User entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete("person", entity);
        session.flush();
    }

    @Override
    public List<User> findAll() {
        List<User> resultList = null;

        try {
            Session session = sessionFactory.getCurrentSession();
            Query<User> query = session.createQuery("from User u join fetch u.role", User.class);
            resultList = query.getResultList();
        } catch (NoResultException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return resultList;
    }

    @Override
    public User findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createNamedQuery("person_FindById", User.class);
        query.setParameter("personId", id);
        return query.getSingleResult();
    }
}
