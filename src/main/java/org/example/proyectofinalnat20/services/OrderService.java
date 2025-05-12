package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    // Define the methods that will be implemented in the OrderServiceImpl class
    void createOrder(Order order);

    Optional<Order> getOrderById(Long id);

    List<Order> getAllOrders();

    void updateOrder(Order order);

    void deleteOrder(Long id);
}
