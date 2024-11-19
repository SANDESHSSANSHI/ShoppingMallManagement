package com.shopping_management.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping_management.demo.model.Cart;
import com.shopping_management.demo.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Create a new cart
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Get all carts
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    // Get a cart by ID
    public Cart getCartById(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id " + cartId));
        return cart; 
    }

    // Update a cart
    public Cart updateCart(Integer cartId, Cart cartDetails) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id " + cartId));

        cart.setCustomerId(cartDetails.getCustomerId());
        cart.setShopId(cartDetails.getShopId());
        cart.setItemIds(cartDetails.getItemIds());

        return cartRepository.save(cart);
    }

    // Delete a cart
    public void deleteCart(Integer cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new RuntimeException("Cart not found with id " + cartId);
        }
        cartRepository.deleteById(cartId);
    }
}
