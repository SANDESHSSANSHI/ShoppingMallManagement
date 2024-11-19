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

import com.shopping_management.demo.dto.CustomerDTO;
import com.shopping_management.demo.model.Cart;
import com.shopping_management.demo.model.Customer;
import com.shopping_management.demo.model.Mall;
import com.shopping_management.demo.model.OrderDetails;
import com.shopping_management.demo.model.Shop;
import com.shopping_management.demo.model.User;
import com.shopping_management.demo.service.CartService;
import com.shopping_management.demo.service.CustomerService;
import com.shopping_management.demo.service.MallService;
import com.shopping_management.demo.service.OrderDetailsService;
import com.shopping_management.demo.service.ShopService;
import com.shopping_management.demo.service.UserService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderDetailsService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private MallService mallService;
    @Autowired
    private ShopService shopService;
    
    @GetMapping
    public List<CustomerDTO> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return customers.stream().map(customer -> {
            User user = userService.getUserById(customer.getCustomerId());
            OrderDetails order = orderService.getOrderById(customer.getOrderId());
            Cart cart = cartService.getCartById(order.getCartId());
            Shop shop = shopService.getShopById(cart.getShopId());
            Mall mall = mallService.getMallById(shop.getMallId());

            return new CustomerDTO(
                customer.getCustomerId(),
                user.getName(),  
                user.getPassword(),  
                user.getMobileNumber(),
                user.getMail(),
                order.getOrderId(),
                cart.getCartid(),
                mall.getMallId(),
                mall.getMallName(),
                shop.getShopId(),
                shop.getShopName()
            );
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            if (customer == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            
            User user = userService.getUserById(customer.getCustomerId());
            OrderDetails order = orderService.getOrderById(customer.getOrderId());
            Cart cart = cartService.getCartById(order.getCartId());
            Shop shop = shopService.getShopById(cart.getShopId());
            Mall mall = mallService.getMallById(shop.getMallId());

            CustomerDTO customerDTO = new CustomerDTO(
                customer.getCustomerId(),
                user.getName(),
                user.getPassword(),  
                user.getMobileNumber(),
                user.getMail(),
                order.getOrderId(),
                cart.getCartid(),
                mall.getMallId(),
                mall.getMallName(),
                shop.getShopId(),
                shop.getShopName()
            );

            return ResponseEntity.ok(customerDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            customerService.createCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error creating customer: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        try {
            if (customerService.getCustomerById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
            }
            customerService.updateCustomer(id, customer);
            return ResponseEntity.ok("Customer updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error updating customer: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        try {
            if (customerService.getCustomerById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
            }
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("Customer deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error deleting customer: " + e.getMessage());
        }
    }
}
