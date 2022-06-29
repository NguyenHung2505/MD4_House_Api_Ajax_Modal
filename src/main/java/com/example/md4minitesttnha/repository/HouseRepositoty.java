package com.example.md4minitesttnha.repository;

import com.example.md4minitesttnha.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepositoty extends JpaRepository<House, Long> {
    Iterable<House> findAllByNameContaining(String name);
}
