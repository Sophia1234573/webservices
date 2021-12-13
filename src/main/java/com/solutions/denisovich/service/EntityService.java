package com.solutions.denisovich.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EntityService<E> {
    boolean create(E entity);
    void update(E entity);
    void remove(E entity);
    List<E> findAll();
    E findById(Long id);
}
