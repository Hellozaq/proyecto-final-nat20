package org.example.proyectofinalnat20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyectofinalnat20.entity.compositeKeys.UserProductId;

/**
 * Entity that represents the many-to-many relationship between User and Product.
 * This class provides complete control over the relationship and allows for additional
 * attributes to be stored in the join table.
 */
@Entity
@Table(name = "user_products")
@Data
@NoArgsConstructor
public class UserProduct {

    @EmbeddedId
    private UserProductId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    public UserProduct(User user, Product product) {
        this.user = user;
        this.product = product;
        this.id = new UserProductId(user.getUserId(), product.getProductId());
    }

    public UserProductId getId() {
        return id;
    }

    public void setId(UserProductId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}