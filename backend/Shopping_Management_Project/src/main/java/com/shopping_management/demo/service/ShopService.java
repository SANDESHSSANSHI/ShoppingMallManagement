package com.shopping_management.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shopping_management.demo.model.Shop;
import com.shopping_management.demo.repository.ShopRepository;

@Service
@Transactional
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    // Create or update shop with image
    public Shop saveShop(Shop shop, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            shop.setImage(imageFile.getBytes()); // Set the image as bytes
        }
        return shopRepository.save(shop);
    }

    // Get all shops
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    // Get a shop by ID
    public Shop getShopById(Integer id) {
        return shopRepository.findById(id).orElse(null);
    }

    // Delete a shop by ID
    public void deleteShop(Integer id) {
        shopRepository.deleteById(id);
    }
    
    public List<Shop> getShopsByMallId(Integer mallId) {
        return shopRepository.findByMallId(mallId);
    }
}
