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

import com.shopping_management.demo.dto.ShopOwnerDTO;
import com.shopping_management.demo.model.Shop;
import com.shopping_management.demo.model.ShopOwner;
import com.shopping_management.demo.model.User;
import com.shopping_management.demo.service.ShopOwnerService;
import com.shopping_management.demo.service.ShopService;
import com.shopping_management.demo.service.UserService;

@RestController
@RequestMapping("/shopowners")
public class ShopOwnerController {

    @Autowired
    private ShopOwnerService shopOwnerService;
    
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;
    
    // Get all shop owners
    @GetMapping
    public List<ShopOwnerDTO> getAllShopOwners() {
        List<ShopOwner> shopOwners = shopOwnerService.getAllShopOwners();
        
        return shopOwners.stream().map(shopOwner -> {
            Shop shop = shopService.getShopById(shopOwner.getShopId());
            User user = userService.getUserById(shopOwner.getShopOwnerId());
            
            return new ShopOwnerDTO(
                shopOwner.getShopOwnerId(),
                shop.getShopName(),
                shopOwner.getShopId(),
                user.getName(),
                user.getPassword(),
                user.getMobileNumber(),
                user.getMail()
            );
        }).collect(Collectors.toList());
    }

    // Get a shop owner by ID
    @GetMapping("/{id}")
    public ResponseEntity<ShopOwnerDTO> getShopOwnerById(@PathVariable Integer id) {
        try {
            ShopOwner shopOwner = shopOwnerService.getShopOwnerById(id);
            if (shopOwner == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Shop shop = shopService.getShopById(shopOwner.getShopId());
            User user = userService.getUserById(shopOwner.getShopOwnerId());

            ShopOwnerDTO shopOwnerDTO = new ShopOwnerDTO(
                shopOwner.getShopOwnerId(),
                shop.getShopName(),
                shopOwner.getShopId(),
                user.getName(),
                user.getPassword(),
                user.getMobileNumber(),
                user.getMail()
            );

            return ResponseEntity.ok(shopOwnerDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Create a new shop owner
    @PostMapping
    public ResponseEntity<String> createShopOwner(@RequestBody ShopOwner shopOwner) {
        try {
            shopOwnerService.createShopOwner(shopOwner);
            return ResponseEntity.status(HttpStatus.CREATED).body("ShopOwner created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating shop owner: " + e.getMessage());
        }
    }

    // Update an existing shop owner
    @PutMapping("/{id}")
    public ResponseEntity<String> updateShopOwner(@PathVariable Integer id, @RequestBody ShopOwner shopOwner) {
        try {
            if (shopOwnerService.getShopOwnerById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ShopOwner not found.");
            }
            shopOwnerService.updateShopOwner(id, shopOwner);
            return ResponseEntity.ok("ShopOwner updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating shop owner: " + e.getMessage());
        }
    }

    // Delete a shop owner
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShopOwner(@PathVariable Integer id) {
        try {
            if (shopOwnerService.getShopOwnerById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ShopOwner not found.");
            }
            shopOwnerService.deleteShopOwner(id);
            return ResponseEntity.ok("ShopOwner deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting shop owner: " + e.getMessage());
        }
    }
}
