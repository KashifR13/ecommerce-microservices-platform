package com.ecommerce.platform.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

}