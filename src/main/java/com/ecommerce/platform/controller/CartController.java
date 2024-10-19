package com.ecommerce.platform.controller;

import com.ecommerce.platform.model.Cart;
import com.ecommerce.platform.model.CartItem;
import com.ecommerce.platform.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/{userId}/add")
    public Cart addItemToCart(@PathVariable Long userId, @RequestBody CartItem item) {
        return cartService.addItemToCart(userId, item);
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public Cart removeItemFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        return cartService.removeItemFromCart(userId, productId);
    }

}