package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    Optional<Product> findByCategory(String category);
    Optional<Product> findByPrice(Double price);
    List<Product> findAll();
    List<Product> findByNameContaining(String name);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    Product save(Product product);
    void deleteById(Long id);
}
