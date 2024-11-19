package com.shopping_management.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shopping_management.demo.model.Item;
import com.shopping_management.demo.repository.ItemRepository;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItemById(Integer id) {
    	
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
//    public Item updateItem(Integer id, Item item, MultipartFile imageFile) throws IOException {
//        Item existingItem = getItemById(id);
//        if (existingItem != null) {
//            if (imageFile != null && !imageFile.isEmpty()) {
//                // Save the new image file to the specified directory
//                String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
//                Path filePath = Paths.get(imagePath, fileName);
//                Files.copy(imageFile.getInputStream(), filePath);
//
//                // Store the new image bytes in the item
//                item.setImage(Files.readAllBytes(filePath));
//            } else {
//                // If no new image, retain the existing image
//                item.setImage(existingItem.getImage());
//            }
//            item.setItemId(id); // Ensure you're setting the correct ID for the update
//            return itemRepository.save(item);
//        }
//        return null; // Or throw an exception if item not found
//    }

    public void deleteItem(Integer id) {
        itemRepository.deleteById(id);
    }

    public List<Item> getItemsByShopId(Integer shopId) {
        return itemRepository.findByShopId(shopId);
    }
    
    public Integer countItemsByShopId(Integer shopId) {
        return itemRepository.countByShopId(shopId);
    }
}
