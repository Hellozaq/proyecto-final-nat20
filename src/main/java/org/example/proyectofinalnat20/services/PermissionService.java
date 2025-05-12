package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll();
    Permission findById(Long id);
    Permission save(Permission permission);
    void deleteById(Long id);
    Permission findByName(String name);
}
