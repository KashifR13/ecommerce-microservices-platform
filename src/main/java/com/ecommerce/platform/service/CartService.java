package com.ecommerce.platform.service;

import com.ecommerce.platform.model.Cart;
import com.ecommerce.platform.model.CartItem;
import com.ecommerce.platform.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElseGet(() -> createAndSaveCart(userId));
    }

    public Cart addItemToCart(Long userId, CartItem item) {
        Cart cart = getCartByUserId(userId);
        List<CartItem> cartItems = cart.getItems();
        cartItems.add(item);
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Long userId, Long productId) {
        Cart cart = getCartByUserId(userId);
        List<CartItem> cartItems = cart.getItems();
        cartItems.removeIf(item -> item.getProductId().equals(productId));
        return cartRepository.save(cart);
    }

    private Cart createAndSaveCart(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        return cartRepository.save(cart);
    }

}