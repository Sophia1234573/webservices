package com.solutions.denisovich.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Dao<E> {
    void create(E entity);
    void update(E entity);
    void remove(E entity);
    List<E> findAll();
    E findById(Long id);
}
