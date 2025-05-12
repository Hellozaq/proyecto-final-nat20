package org.example.proyectofinalnat20.repository;

import org.example.proyectofinalnat20.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
