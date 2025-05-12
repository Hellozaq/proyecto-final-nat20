package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findById(Long id);
    List<Category> findAll();
    Category save(Category category);
    void deleteById(Long id);
    Category findByName(String name);
    List<Category> findByUserId(Long userId);
}
