package com.example.md4minitesttnha.service;

import com.example.md4minitesttnha.model.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HouseService extends GeneraService<House>{
    Iterable<House> findAllByNameContaining(String name);
}
