package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.UserProduct;

import java.util.List;
import java.util.Optional;

public interface UserProductService {


    Optional<UserProduct> findById(Long id);
    Optional<UserProduct> save(UserProduct userProduct);
    void deleteById(Long id);
    Optional<UserProduct> findByName(String name);
    Optional<UserProduct> findByDescription(String description);
    Boolean existsById(Long id);
    List<UserProduct> findAll();
}
