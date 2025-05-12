package org.example.proyectofinalnat20.repository;

import org.example.proyectofinalnat20.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
