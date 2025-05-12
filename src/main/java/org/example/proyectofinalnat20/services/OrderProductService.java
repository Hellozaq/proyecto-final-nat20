package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.OrderProduct;

import java.util.List;
import java.util.Optional;

public interface OrderProductService {

    // Define the methods that will be implemented in the service class
    void createOrderProduct(OrderProduct orderProduct);

    Optional<OrderProduct> getOrderProductById(Long id);

    List<OrderProduct> getAllOrderProducts();

    void updateOrderProduct(OrderProduct orderProduct);

    void deleteOrderProduct(Long id);
}
