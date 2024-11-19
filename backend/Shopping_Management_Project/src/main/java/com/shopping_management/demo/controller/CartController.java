package com.shopping_management.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping_management.demo.dto.CartDTO;
import com.shopping_management.demo.model.Cart;
import com.shopping_management.demo.model.Item;
import com.shopping_management.demo.model.Shop;
import com.shopping_management.demo.model.User;
import com.shopping_management.demo.service.CartService;
import com.shopping_management.demo.service.ItemService;
import com.shopping_management.demo.service.ShopService;
import com.shopping_management.demo.service.UserService;

@RestController
@RequestMapping("carts")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userservice;
    @Autowired
    private ShopService shopservice;
    @Autowired
    private ItemService itemservice;

    // Create a new cart
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart savedCart = cartService.createCart(cart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    // Retrieve all carts
    @GetMapping
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts(); 

        return carts.stream().map(cart -> {
            // Ensure valid customer and shop IDs before fetching
            User user = userservice.getUserById(cart.getCustomerId());
            Shop shop = shopservice.getShopById(cart.getShopId());

            if (user == null || shop == null) {
                return null; // Skipping carts with invalid data
            }
            
            // Safeguard for items being null
            List<Integer> itemIds = cart.getItemIds();
            Integer numberOfItems = (itemIds != null) ? cart.getItemCount(itemIds) : 0;
            
            // Calculate total price of items in the cart
            Double totalPrice = 0.0;
            if (itemIds != null) {
                for (Integer itemId : itemIds) {
                    Item item = itemservice.getItemById(itemId);
                    if (item != null) {
                        totalPrice += item.getItemPrice();
                    }
                }
            }

            // Build and return CartDTO
            return new CartDTO(
                cart.getCartid(),
                cart.getCustomerId(),
                user.getName(),
                shop.getShopId(),
                shop.getShopName(),
                numberOfItems,
                totalPrice
            );
        }).filter(cartDTO -> cartDTO != null)  // Filter out any null results due to invalid carts
          .collect(Collectors.toList());
    }


    // Retrieve a specific cart by its ID
    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Integer id) {
        try {
            Cart cart = cartService.getCartById(id);
            
            if (cart == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            }

            User user = userservice.getUserById(cart.getCustomerId());
            Shop shop = shopservice.getShopById(cart.getShopId());

            Integer numberOfItems = cart.getItemCount(cart.getItemIds());
            List<Integer> itemIds = cart.getItemIds();
            Double totalPrice = 0.0;

            if (itemIds != null) {
                for (Integer itemId : itemIds) {
                    Item item = itemservice.getItemById(itemId);
                    if (item != null) {
                        totalPrice += item.getItemPrice();
                    }
                }
            }

            CartDTO cartDTO = new CartDTO(
                cart.getCartid(),
                cart.getCustomerId(),
                user.getName(),
                shop.getShopId(),
                shop.getShopName(),
                numberOfItems,
                totalPrice
            );

            return ResponseEntity.ok(cartDTO); 
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing cart by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Integer id, @RequestBody Cart cartDetails) {
        Cart updatedCart = cartService.updateCart(id, cartDetails);
        return ResponseEntity.ok(updatedCart);
    }

    // Delete a cart by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
