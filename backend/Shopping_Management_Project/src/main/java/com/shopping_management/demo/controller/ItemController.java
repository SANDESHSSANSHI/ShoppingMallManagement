package com.shopping_management.demo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping_management.demo.dto.ItemDTO;
import com.shopping_management.demo.model.Item;
import com.shopping_management.demo.model.Mall;
import com.shopping_management.demo.model.Shop;
import com.shopping_management.demo.service.ItemService;
import com.shopping_management.demo.service.MallService;
import com.shopping_management.demo.service.ShopService;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "http://localhost:3001") // Allow requests from React app
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private MallService mallService;

    @GetMapping
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return items.stream().map(item -> {
            Shop shop = shopService.getShopById(item.getShopId());
            Mall mall = mallService.getMallById(shop.getMallId());
            return new ItemDTO(
                item.getItemId(),
                item.getItemName(),
                item.getItemManufacture(),
                item.getItemExpiryDate(),
                item.getItemPrice(),
                item.getItemCategory(),
                item.getShopId(),
                shop.getShopName(),
                mall.getMallId(),
                mall.getMallName(),
                item.getImage() // Include image
            );
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Integer id) {
        try {
            Item item = itemService.getItemById(id);
            if (item != null) {
                Shop shop = shopService.getShopById(item.getShopId());
                Mall mall = mallService.getMallById(shop.getMallId());

                ItemDTO itemDTO = new ItemDTO(
                    item.getItemId(),
                    item.getItemName(),
                    item.getItemManufacture(),
                    item.getItemExpiryDate(),
                    item.getItemPrice(),
                    item.getItemCategory(),
                    item.getShopId(),
                    shop.getShopName(),
                    mall.getMallId(),
                    mall.getMallName(),
                    item.getImage() // Include image
                );
                return ResponseEntity.ok(itemDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Item> addItem(
            @RequestParam("itemId") Integer itemId, // Ensure itemId is passed in the request
            @RequestParam("itemName") String itemName,
            @RequestParam("itemManufacture") String itemManufacture,
            @RequestParam("itemExpiryDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate itemExpiryDate,
            @RequestParam("itemPrice") Double itemPrice,
            @RequestParam("itemCategory") String itemCategory,
            @RequestParam("shopId") Integer shopId,
            @RequestParam("image") MultipartFile image) {
        try {
            Item item = new Item();
            item.setItemId(itemId); // Set the itemId here
            item.setItemName(itemName);
            item.setItemManufacture(itemManufacture);
            item.setItemExpiryDate(itemExpiryDate);
            item.setItemPrice(itemPrice);
            item.setItemCategory(itemCategory);
            item.setShopId(shopId);
            item.setImage(image.getBytes()); // Set the image as bytes

            // Save the item using the service layer
            return new ResponseEntity<>(itemService.saveItem(item), HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{itemId}", consumes = {"multipart/form-data"})
    public ResponseEntity<Item> updateItem(
            @PathVariable("itemId") Integer itemId,
            @RequestParam("itemName") String itemName,
            @RequestParam("itemManufacture") String itemManufacture,
            @RequestParam("itemExpiryDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate itemExpiryDate,
            @RequestParam("itemPrice") Double itemPrice,
            @RequestParam("itemCategory") String itemCategory,
            @RequestParam("shopId") Integer shopId,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            Item existingItem = itemService.getItemById(itemId);
            if (existingItem == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Update the existing item's fields
            existingItem.setItemName(itemName);
            existingItem.setItemManufacture(itemManufacture);
            existingItem.setItemExpiryDate(itemExpiryDate);
            existingItem.setItemPrice(itemPrice);
            existingItem.setItemCategory(itemCategory);
            existingItem.setShopId(shopId);
            
            // Update image only if a new one is provided
            if (image != null && !image.isEmpty()) {
                existingItem.setImage(image.getBytes()); // Set the new image as bytes
            }

            return new ResponseEntity<>(itemService.saveItem(existingItem), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateItem(@PathVariable Integer id, @RequestParam("item") String itemJson, @RequestParam("image") MultipartFile imageFile) {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            Item item = objectMapper.readValue(itemJson, Item.class);
//
//            if (imageFile != null && !imageFile.isEmpty()) {
//                byte[] imageBytes = imageFile.getBytes();
//                item.setImage(imageBytes); // Set the image to the item
//            }
//
//            if (itemService.getItemById(id) == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
//            }
//            itemService.updateItem(id, item);
//            return ResponseEntity.ok("Item updated successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error updating item: " + e.getMessage());
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Integer id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.ok("Item deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting item: " + e.getMessage());
        }
    }
}
