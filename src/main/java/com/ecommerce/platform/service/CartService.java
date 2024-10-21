package com.ecommerce.platform.service;

import com.ecommerce.platform.model.Cart;
import com.ecommerce.platform.model.CartItem;
import com.ecommerce.platform.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElse(null);
    }

    public Cart addItemToCart(Long userId, CartItem item) {
        Cart cart = getOrCreateCart(userId);
        cart.getCartItems().add(item);
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Long userId, Long productId) {
        Cart cart = getCartByUserId(userId);
        if (cart != null) {
            cart.getCartItems().removeIf(cartItem -> cartItem.getProductId().equals(productId));
            return cartRepository.save(cart);
        }
        return null;
    }

    private Cart getOrCreateCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        if (cart == null) {
            cart = new Cart(userId);
        }
        return cart;
    }

}