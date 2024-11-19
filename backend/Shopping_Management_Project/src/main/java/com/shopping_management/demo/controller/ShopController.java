package com.shopping_management.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopping_management.demo.dto.ShopDTO;
import com.shopping_management.demo.dto.ShopEmpDTO;
import com.shopping_management.demo.model.Employee;
import com.shopping_management.demo.model.Item;
import com.shopping_management.demo.model.Mall;
import com.shopping_management.demo.model.Shop;
import com.shopping_management.demo.model.User;
import com.shopping_management.demo.service.EmployeeService;
import com.shopping_management.demo.service.ItemService;
import com.shopping_management.demo.service.MallService;
import com.shopping_management.demo.service.ShopService;
import com.shopping_management.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3001")

@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    @Autowired
    private MallService mallService;
    
    @Autowired
    private ItemService itemService;

    @Autowired
    private EmployeeService employeeService;

    // Get all shops
    @GetMapping
    public List<ShopDTO> getAllShops() {
        List<Shop> shops = shopService.getAllShops();
        return shops.stream().map(shop -> {
            User user = userService.getUserById(shop.getShopOwnerId());
            Mall mall = mallService.getMallById(shop.getMallId());
            Integer noOfEmployees = employeeService.countEmployeesByShopId(shop.getShopId());
            Integer noOfItems= itemService.countItemsByShopId(shop.getShopId());

            
            return new ShopDTO(
                shop.getShopId(),
                shop.getShopName(),
                shop.getShopOwnerId(),
                user.getName(),
                shop.getShopStatus(),
                shop.getMallId(),
                mall.getMallName(),
                noOfItems,
                noOfEmployees
            );
        }).collect(Collectors.toList());
    }


    
    @GetMapping("/{id}")
    public ResponseEntity<ShopEmpDTO> getShopById(@PathVariable Integer id) {
        try {
            // Fetch shop, user, mall, and count of employees as before
            Shop shop = shopService.getShopById(id);
            User user = userService.getUserById(shop.getShopOwnerId());
            Mall mall = mallService.getMallById(shop.getMallId());
            Integer noOfEmployees = employeeService.countEmployeesByShopId(shop.getShopId());

            // Fetch employees associated with the shop
            List<Employee> employees = employeeService.getEmployeesByShopId(shop.getShopId());

            // Fetch items associated with the shop
            List<Item> items = itemService.getItemsByShopId(shop.getShopId());

            // Create ShopEmpDTO with full Employee and Item objects
            ShopEmpDTO shopEmpDTO = new ShopEmpDTO(
                shop.getShopId(),
                shop.getShopName(),
                shop.getShopOwnerId(),
                user.getName(),
                shop.getShopStatus(),
                shop.getMallId(),
                mall.getMallName(),
                noOfEmployees,
                employees, // Pass the full Employee list
                items      // Pass the full Item list
            );

            return ResponseEntity.ok(shopEmpDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    
//    List<Shops_for_mallDTO> shopDTOs = shops.stream().map(shop -> new Shops_for_mallDTO(
//            shop.getShopId(),
//            shop.getShopName()
//    )).collect(Collectors.toList());
//
//    
    

//    @PostMapping
//    public ResponseEntity<String> createShop(@RequestBody Shop shop) {
//        try {
//            shopService.createShop(shop);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Shop created successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error creating shop: " + e.getMessage());
//        }
//    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Shop> addShop(
            @RequestParam("shopId") Integer shopId,
            @RequestParam("shopName") String shopName,
            @RequestParam("shopStatus") String shopStatus,
            @RequestParam("shopOwnerId") Integer shopOwnerId,
            @RequestParam("mallId") Integer mallId,
            @RequestParam("image") MultipartFile image) {
        try {
            Shop shop = new Shop();
            shop.setShopId(shopId);
            shop.setShopName(shopName);
            shop.setShopStatus(shopStatus);
            shop.setShopOwnerId(shopOwnerId);
            shop.setMallId(mallId);
            return new ResponseEntity<>(shopService.saveShop(shop, image), HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    // Update an existing shop
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateShop(@PathVariable Integer id, @RequestBody Shop shop) {
//        try {
//            if (shopService.getShopById(id) == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found.");
//            }
//            shopService.updateShop(id, shop);
//            return ResponseEntity.ok("Shop updated successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error updating shop: " + e.getMessage());
//        }
//    }

    @PutMapping(value = "/{shopId}", consumes = {"multipart/form-data"})
    public ResponseEntity<Shop> updateShop(
            @PathVariable("shopId") Integer shopId,
            @RequestParam("shopName") String shopName,
            @RequestParam("shopStatus") String shopStatus,
            @RequestParam("shopOwnerId") Integer shopOwnerId,
            @RequestParam("mallId") Integer mallId,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            Shop existingShop = shopService.getShopById(shopId);
            if (existingShop == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Update fields
            existingShop.setShopName(shopName);
            existingShop.setShopStatus(shopStatus);
            existingShop.setShopOwnerId(shopOwnerId);
            existingShop.setMallId(mallId);

            return new ResponseEntity<>(shopService.saveShop(existingShop, image), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a shop
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable Integer id) {
        try {
            if (shopService.getShopById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found.");
            }
            shopService.deleteShop(id);
            return ResponseEntity.ok("Shop deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting shop: " + e.getMessage());
        }
    }
}
