package org.example.proyectofinalnat20.repository;

import org.example.proyectofinalnat20.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
