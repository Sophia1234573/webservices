package com.solutions.denisovich.service.implementations;

import com.solutions.denisovich.dao.UserDao;
import com.solutions.denisovich.model.User;
import com.solutions.denisovich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean create(User user) {
        User userFromDB = userDao.findByLogin(user.getLogin());
        if (userFromDB != null) {
            return false;
        }
        userDao.create(user);
        return true;
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional
    public void remove(User entity) {
        userDao.remove(entity);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userDao.findAll();
    }

    public Map<User, Integer> getUsersWithAge(List<User> users) {
        return users.stream()
                .collect(Collectors.toMap(Function.identity(), user -> calculateAge(user.getBirthday())));
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public int calculateAge(Date birthday) {
        LocalDate now = LocalDate.now();
        LocalDate birt = Instant.ofEpochMilli(birthday.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return Period.between(birt, now).getYears();
    }

    @Override
    @Transactional
    public boolean isLoginAlreadyExists(String login) {
        boolean userInDb = true;
        if (userDao.findByLogin(login) == null) userInDb = false;
        return userInDb;
    }
}
