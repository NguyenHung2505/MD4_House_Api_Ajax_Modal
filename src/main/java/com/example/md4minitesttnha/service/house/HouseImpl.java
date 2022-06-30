package com.example.md4minitesttnha.service.house;

import com.example.md4minitesttnha.model.House;
import com.example.md4minitesttnha.repository.HouseRepositoty;
import com.example.md4minitesttnha.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseImpl implements HouseService {
    @Autowired
    HouseRepositoty houseRepositoty;


    @Override
    public List<House> findAll() {
        return houseRepositoty.findAll();
    }

    @Override
    public Optional<House> findById(Long id) {
        return houseRepositoty.findById(id);
    }

    @Override
    public void save(House house) {
        houseRepositoty.save(house);
    }

    @Override
    public void remove(Long id) {
        houseRepositoty.deleteById(id);
    }

    @Override
    public Iterable<House> findAllByNameContaining(String name) {
        return houseRepositoty.findAllByNameContaining(name);
    }
}
