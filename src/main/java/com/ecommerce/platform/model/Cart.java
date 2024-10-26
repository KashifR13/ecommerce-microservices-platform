package com.ecommerce.platform.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    public Cart(List<CartItem> items) {
        this.cartItems = items;
    }

    public Cart(Long userId) {
        this.userId = userId;
        this.cartItems = new ArrayList<>();
    }

    public Cart() {}

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long id) {
        this.cartId = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCartItems(List<CartItem> items) {
        this.cartItems = items;
    }

    public Long getUserId() {
        return userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

}