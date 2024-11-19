package com.shopping_management.demo.service;

import com.shopping_management.demo.model.ShopOwner;
import com.shopping_management.demo.repository.ShopOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopOwnerService {

    @Autowired
    private ShopOwnerRepository shopOwnerRepository;

    // Get all shop owners
    public List<ShopOwner> getAllShopOwners() {
        return shopOwnerRepository.findAll();
    }

    // Get a shop owner by ID
    public ShopOwner getShopOwnerById(Integer id) {
        return shopOwnerRepository.findById(id).orElse(null);
    }

    // Create a new shop owner
    public void createShopOwner(ShopOwner shopOwner) {
        shopOwnerRepository.save(shopOwner);
    }

    // Update an existing shop owner
    public void updateShopOwner(Integer id, ShopOwner shopOwner) {
        shopOwner.setShopOwnerId(id);
        shopOwnerRepository.save(shopOwner);
    }

    // Delete a shop owner by ID
    public void deleteShopOwner(Integer id) {
        shopOwnerRepository.deleteById(id);
    }
}
