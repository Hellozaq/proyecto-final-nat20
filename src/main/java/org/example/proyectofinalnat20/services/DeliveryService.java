package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {

    Optional<Delivery> findById(Long id);
    List<Delivery> findAll();
    Optional<Delivery> save(Delivery delivery);
    void deleteById(Long id);
    Optional<Delivery> findByDeliveryId(Long deliveryId);
    List<Delivery> findByUserId(Long userId);
}
