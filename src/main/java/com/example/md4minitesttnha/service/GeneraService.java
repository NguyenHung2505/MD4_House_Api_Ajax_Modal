package com.example.md4minitesttnha.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GeneraService<A>{
    List<A> findAll();

    Optional<A> findById(Long id);

    void save(A a);

    void remove(Long id);
}
