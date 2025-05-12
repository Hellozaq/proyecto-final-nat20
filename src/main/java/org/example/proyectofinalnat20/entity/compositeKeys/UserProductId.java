package org.example.proyectofinalnat20.entity.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Composite key class for the UserProduct entity.
 * This class is used to represent the composite primary key for the many-to-many
 * relationship between User and Product entities.
 */
@Embeddable
@Data
@NoArgsConstructor
public class UserProductId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

    public UserProductId(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProductId that = (UserProductId) o;

        if (!userId.equals(that.userId)) return false;
        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + productId.hashCode();
        return result;
    }
}
