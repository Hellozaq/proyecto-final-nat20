package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.Discount;

import java.util.List;
import java.util.Optional;

public interface DiscountService {

    Optional<Discount> findById(Long id);
    List<Discount> findAll();
    Optional<Discount> save(Discount discount);
    void deleteById(Long id);
    Optional<Discount> findByName(String name);
    List<Discount> findByValue(Double value);
}
