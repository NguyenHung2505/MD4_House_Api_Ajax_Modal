package com.example.md4minitesttnha.service;


import com.example.md4minitesttnha.model.Role;


public interface RoleService {
    Iterable<Role> findAll();


    void save(Role role);

    Role findByName(String name);
}
