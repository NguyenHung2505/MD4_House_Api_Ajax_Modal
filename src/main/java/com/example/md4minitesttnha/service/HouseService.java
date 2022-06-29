package com.example.md4minitesttnha.service;

import com.example.md4minitesttnha.model.House;

public interface HouseService extends GeneraService<House>{
    Iterable<House> findAllByNameContaining(String name);
}
