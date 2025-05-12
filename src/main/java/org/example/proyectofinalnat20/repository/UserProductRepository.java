package org.example.proyectofinalnat20.repository;

import org.example.proyectofinalnat20.entity.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> {
}
