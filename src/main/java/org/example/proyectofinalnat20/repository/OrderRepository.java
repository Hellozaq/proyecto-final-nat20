package org.example.proyectofinalnat20.repository;

import org.example.proyectofinalnat20.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
