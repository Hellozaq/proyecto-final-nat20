package org.example.proyectofinalnat20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinalnat20.entity.compositeKeys.ProductDiscountId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity that represents the many-to-many relationship between Product and Discount.
 * This class allows for control over which products have specific discounts applied,
 * and can include product-specific discount rules and properties.
 */
@Entity
@Table(name = "product_discounts")
@Data
@NoArgsConstructor
public class ProductDiscount {

    @EmbeddedId
    private ProductDiscountId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("discountId")
    @JoinColumn(name = "discount_id")
    private Discount discount;

    public ProductDiscountId getId() {
        return id;
    }

    public void setId(ProductDiscountId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
