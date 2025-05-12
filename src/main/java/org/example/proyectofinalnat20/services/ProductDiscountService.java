package org.example.proyectofinalnat20.services;

public interface ProductDiscountService {

    // Define the methods that will be implemented in the service class
    void applyDiscountToProduct(Long productId, Double discountPercentage);

    void removeDiscountFromProduct(Long productId);

    Double getDiscountedPrice(Long productId);

    void setDiscountForAllProducts(Double discountPercentage);

    void removeDiscountForAllProducts();
}
