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

import com.shopping_management.demo.dto.MallAdminDTO;
import com.shopping_management.demo.model.Mall;
import com.shopping_management.demo.model.MallAdmin;
import com.shopping_management.demo.model.User;
import com.shopping_management.demo.service.MallAdminService;
import com.shopping_management.demo.service.MallService;
import com.shopping_management.demo.service.UserService;

@RestController
@RequestMapping("/malladmins")
public class MallAdminController {

    @Autowired
    private MallAdminService mallAdminService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private MallService mallService;
    
    @GetMapping
    public List<MallAdminDTO> getMallAdmins() {
        List<MallAdmin> mallAdmins = mallAdminService.getMallAdmins();
        return mallAdmins.stream().map(mallAdmin -> {
            Mall mall = mallService.getMallById(mallAdmin.getMallId());
            User user = userService.getUserById(mallAdmin.getMallAdminId());

            return new MallAdminDTO(
                mallAdmin.getMallAdminId(),
                user.getName(),
                mallAdmin.getMallId(),
                mall.getMallName(),
                user.getPassword(),
                user.getMobileNumber(),
                user.getMail()
            );
        }).collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MallAdminDTO> getMallAdminById(@PathVariable Integer id) {
        try {
            MallAdmin mallAdmin = mallAdminService.getMallAdminById(id);
            Mall mall = mallService.getMallById(mallAdmin.getMallId());
            User user = userService.getUserById(mallAdmin.getMallAdminId());

            MallAdminDTO mallAdminDTO = new MallAdminDTO(
                mallAdmin.getMallAdminId(),
                user.getName(),
                mallAdmin.getMallId(),
                mall.getMallName(),
                user.getPassword(), 
                user.getMobileNumber(),
                user.getMail()
            );

            return ResponseEntity.status(HttpStatus.FOUND).body(mallAdminDTO);        
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createMallAdmin(@RequestBody MallAdmin mallAdmin) {
        try {
            mallAdminService.createMallAdmin(mallAdmin);
            return ResponseEntity.status(HttpStatus.CREATED).body("Mall admin created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error creating mall admin: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMallAdmin(@PathVariable Integer id, @RequestBody MallAdmin mallAdmin) {
        try {
            if (mallAdminService.getMallAdminById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mall admin not found.");
            }
            mallAdminService.updateMallAdmin(id, mallAdmin);
            return ResponseEntity.ok("Mall admin updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error updating mall admin: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMallAdmin(@PathVariable Integer id) {
        try {
            if (mallAdminService.getMallAdminById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mall admin not found.");
            }
            mallAdminService.deleteMallAdmin(id);
            return ResponseEntity.ok("Mall admin deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error deleting mall admin: " + e.getMessage());
        }
    }
}
