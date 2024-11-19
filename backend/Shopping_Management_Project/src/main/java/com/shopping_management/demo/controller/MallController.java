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

import com.shopping_management.demo.dto.MallDTO;
import com.shopping_management.demo.dto.Shops_for_mallDTO;
import com.shopping_management.demo.dto.Shops_in_mallDTO;
import com.shopping_management.demo.model.Mall;
import com.shopping_management.demo.model.Shop;
import com.shopping_management.demo.service.MallService;
import com.shopping_management.demo.service.ShopService;

@RestController
@RequestMapping("/malls")
public class MallController {

    @Autowired
    private MallService mallService;
    @Autowired
    private ShopService shopService;

    @GetMapping
    public List<MallDTO> getAllMalls() {
        List<Mall> malls = mallService.getAllMalls();

        return malls.stream().map(mall -> {
            Integer no_of_shops = mallService.getNumberOfShopsByMallId(mall.getMallId());

            return new MallDTO(
                    mall.getMallId(),
                    mall.getMallName(),
                    mall.getMallAdminId(),
                    mall.getMallLocation(),
                    mall.getMallCategory(),
                    no_of_shops
            );	
        }).collect(Collectors.toList());
    }
//

//    @GetMapping("/{id}")
//    public ResponseEntity<MallDTO> getMallById(@PathVariable Integer id) {
//        try {
//            Mall mall = mallService.getMallById(id);
//            if (mall != null) {
//                Integer no_of_shops = mallService.getNumberOfShopsByMallId(mall.getMallId());
//                MallDTO mallDTO = new MallDTO(
//                        mall.getMallId(),
//                        mall.getMallName(),
//                        mall.getMallAdminId(),
//                        mall.getMallLocation(),
//                        mall.getMallCategory(),
//                        no_of_shops
//                );
//                return ResponseEntity.ok(mallDTO);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
 
    @GetMapping("/{id}")
    public Shops_in_mallDTO getMallById(@PathVariable Integer id) {
        Mall mall = mallService.getMallById(id);  // Assuming you have a method to get a mall by ID
        Integer noOfShops = mallService.getNumberOfShopsByMallId(mall.getMallId());

        // Fetch the shops for the mall and return the full list of Shop objects
        List<Shop> shops = mallService.getShopsByMallId(mall.getMallId());

        // Return the mall details along with the full shop objects
        return new Shops_in_mallDTO(
            mall.getMallId(),
            mall.getMallName(),
            mall.getMallAdminId(),
            mall.getMallLocation(),
            mall.getMallCategory(),
            noOfShops,
            shops // Return the list of full Shop objects here
        );
    }


   

    @PostMapping
    public ResponseEntity<String> createMall(@RequestBody Mall mall) {
        try {
            mallService.createMall(mall);
            return ResponseEntity.status(HttpStatus.CREATED).body("Mall created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating mall: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateMall(@PathVariable Integer id, @RequestBody Mall mall) {
        try {
            if (mallService.getMallById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mall not found.");
            }
            mallService.updateMall(id, mall);
            return ResponseEntity.ok("Mall updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating mall: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMall(@PathVariable Integer id) {
        try {
            if (mallService.getMallById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mall not found.");
            }
            mallService.deleteMall(id);
            return ResponseEntity.ok("Mall deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting mall: " + e.getMessage());
        }
    }
}
