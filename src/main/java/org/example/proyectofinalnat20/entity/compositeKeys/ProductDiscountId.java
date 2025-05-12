package org.example.proyectofinalnat20.entity.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Composite key class for the ProductDiscount entity.
 * This class represents the composite primary key for the many-to-many
 * relationship between Product and Discount entities.
 */
@Embeddable
@Data
@NoArgsConstructor
public class ProductDiscountId implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "discount_id")
    private Long discountId;

    public ProductDiscountId(Long productId, Long discountId) {
        this.productId = productId;
        this.discountId = discountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDiscountId that = (ProductDiscountId) o;

        if (!productId.equals(that.productId)) return false;
        return discountId.equals(that.discountId);
    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + discountId.hashCode();
        return result;
    }
}
