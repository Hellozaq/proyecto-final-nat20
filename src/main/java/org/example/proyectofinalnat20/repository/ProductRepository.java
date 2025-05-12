package org.example.proyectofinalnat20.repository;

import org.example.proyectofinalnat20.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
