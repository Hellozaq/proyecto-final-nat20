package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodService {

    Optional<PaymentMethod> findById(Long id);
    List<PaymentMethod> findAll();
    Optional<PaymentMethod> save(PaymentMethod paymentMethod);
    void deleteById(Long id);

}
