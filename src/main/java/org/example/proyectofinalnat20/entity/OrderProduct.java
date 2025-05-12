package org.example.proyectofinalnat20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinalnat20.entity.compositeKeys.OrderProductId;

import java.math.BigDecimal;

/**
 * Entity that represents the many-to-many relationship between Order and Product.
 * This class functions as an order detail or order item, containing information
 * about products within an order, including quantity, price at time of purchase,
 * and other order-specific details.
 */
@Entity
@Table(name = "order_products")
@Data
@NoArgsConstructor
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Quantity of this product in the order
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Subtotal for this order item (quantity * unitPrice)
     */
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    public OrderProductId getId() {
        return id;
    }

    public void setId(OrderProductId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}