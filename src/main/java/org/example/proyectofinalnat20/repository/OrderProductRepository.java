package org.example.proyectofinalnat20.repository;

import org.example.proyectofinalnat20.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
