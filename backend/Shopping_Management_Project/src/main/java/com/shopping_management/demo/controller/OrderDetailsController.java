package com.shopping_management.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopping_management.demo.dto.ItemDetailsDTO;
import com.shopping_management.demo.dto.OrderDTO;
import com.shopping_management.demo.dto.OrderDetailsDTO;
import com.shopping_management.demo.model.Cart;
import com.shopping_management.demo.model.Item;
import com.shopping_management.demo.model.Mall;
import com.shopping_management.demo.model.OrderDetails;
import com.shopping_management.demo.model.Shop;
import com.shopping_management.demo.model.User;
import com.shopping_management.demo.service.CartService;
import com.shopping_management.demo.service.ItemService;
import com.shopping_management.demo.service.MallService;
import com.shopping_management.demo.service.OrderDetailsService;
import com.shopping_management.demo.service.ShopService;
import com.shopping_management.demo.service.UserService;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private MallService mallService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ItemService itemService;

//    @GetMapping("/customer/{id}")
//    public ResponseEntity<OrderDetails> getOrderByCustomerId(@PathVariable Integer id) {
//        try {
//            OrderDetails order = orderDetailsService.getOrderByCustomerId(id);
//            if (order == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Order not found
//            }
//            return ResponseEntity.ok(order);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDetailsDTO>> getOrdersByCustomerId(@PathVariable Integer id) {
        try {
            // Fetch all order details for the customer ID
            List<OrderDetails> orders = orderDetailsService.getOrdersByCustomerId(id);
            
            if (orders.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // No orders found
            }

            // Convert each OrderDetails to OrderDetailsDTO
            List<OrderDetailsDTO> orderDetailsDTOs = orders.stream()
                .map(order -> {
                    Cart cart = cartService.getCartById(order.getCartId());
                    if (cart == null) {
                        // Log and handle missing cart case
                        return null; // Or handle this case appropriately
                    }

                    User user = userService.getUserById(cart.getCustomerId());
                    if (user == null) {
                        // Log and handle missing user case
                        return null; // Or handle this case appropriately
                    }

                    Shop shop = shopService.getShopById(cart.getShopId());
                    if (shop == null) {
                        // Log and handle missing shop case
                        return null; // Or handle this case appropriately
                    }

                    Mall mall = mallService.getMallById(shop.getMallId());
                    if (mall == null) {
                        // Log and handle missing mall case
                        return null; // Or handle this case appropriately
                    }

                    List<ItemDetailsDTO> itemDetails = cart.getItemIds().stream()
                        .map(itemId -> {
                            Item item = itemService.getItemById(itemId);
                            return item != null ? new ItemDetailsDTO(item.getItemId(), item.getItemName()) : null;
                        })
                        .filter(itemDTO -> itemDTO != null) // Filter out null ItemDetailsDTOs
                        .collect(Collectors.toList());

                    Double totalPrice = itemDetails.stream()
                        .map(itemDTO -> itemService.getItemById(itemDTO.getItemId()))
                        .filter(item -> item != null) // Ensure the item is not null
                        .map(Item::getItemPrice)
                        .reduce(0.0, Double::sum);

                    return new OrderDetailsDTO(
                        order.getOrderId(),
                        order.getCartId(),
                        cart.getCustomerId(),
                        user.getName(),
                        mall.getMallId(),
                        mall.getMallName(),
                        shop.getShopId(),
                        shop.getShopName(),
                        itemDetails.size(),
                        itemDetails,
                        totalPrice,
                        order.getDateOfPurchase(),
                        order.getPaymentMode(),
                        user.getId()
                    );

                })
                .filter(orderDTO -> orderDTO != null) // Remove null OrderDTOs
                .collect(Collectors.toList());

            return ResponseEntity.ok(orderDetailsDTOs);
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    private OrderDetailsDTO convertToDTO(OrderDetails order) {
        Cart cart = cartService.getCartById(order.getCartId());
        User user = userService.getUserById(cart.getCustomerId());
        Shop shop = shopService.getShopById(cart.getShopId());
        Mall mall = mallService.getMallById(shop.getMallId());

        List<ItemDetailsDTO> itemDetails = cart.getItemIds().stream()
            .map(itemId -> {
                Item item = itemService.getItemById(itemId);
                return new ItemDetailsDTO(item.getItemId(), item.getItemName());
            })
            .collect(Collectors.toList());

        Double totalPrice = itemDetails.stream()
            .map(itemDTO -> itemService.getItemById(itemDTO.getItemId()).getItemPrice())
            .reduce(0.0, Double::sum);

        return new OrderDetailsDTO(
            order.getOrderId(),
            order.getCartId(),
            cart.getCustomerId(),
            user.getName(),
            mall.getMallId(),
            mall.getMallName(),
            shop.getShopId(),
            shop.getShopName(),
            itemDetails.size(),
            itemDetails,
            totalPrice,
            order.getDateOfPurchase(),
            order.getPaymentMode(),
            user.getId()
        );
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        try {
            List<OrderDetails> orders = orderDetailsService.getAllOrders();
            List<OrderDTO> orderDetailsDTOList = orders.stream()
                .map(order -> {
                    Cart cart = cartService.getCartById(order.getCartId());
                    if (cart == null) {
                        // Log and return null or handle this case appropriately
                        return null; // Or return an OrderDTO with default values
                    }

                    User user = userService.getUserById(cart.getCustomerId());
                    if (user == null) {
                        // Log and handle the missing user case
                        return null; // Or return an OrderDTO with default values
                    }

                    Shop shop = shopService.getShopById(cart.getShopId());
                    if (shop == null) {
                        // Log and handle the missing shop case
                        return null; // Or return an OrderDTO with default values
                    }

                    Mall mall = mallService.getMallById(shop.getMallId());
                    if (mall == null) {
                        // Log and handle the missing mall case
                        return null; // Or return an OrderDTO with default values
                    }

                    Double totalPrice = cart.getItemIds().stream()
                        .map(itemService::getItemById)
                        .filter(item -> item != null) // Filter out null items
                        .map(Item::getItemPrice)
                        .reduce(0.0, Double::sum);

                    return new OrderDTO(
                        order.getOrderId(),
                        order.getCartId(),
                        cart.getCustomerId(),
                        user.getName(),
                        mall.getMallName(),
                        shop.getShopName(),
                        cart.getItemIds().size(),
                        order.getDateOfPurchase(),
                        totalPrice,
                        order.getPaymentMode()
                    );
                })
                .filter(orderDTO -> orderDTO != null) // Remove null OrderDTOs
                .collect(Collectors.toList());

            return ResponseEntity.ok(orderDetailsDTOList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of()); // Return an empty list in case of error
        }
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDetails orderDetails) {
        try {
            orderDetailsService.createOrder(orderDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error creating order: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsDTO> getOrderById(@PathVariable Integer id) {
        try {
            // Fetch the order details by ID
            Optional<OrderDetails> orderOpt = Optional.ofNullable(orderDetailsService.getOrderById(id));
            
            if (orderOpt.isPresent()) {
                OrderDetails order = orderOpt.get();
                Cart cart = cartService.getCartById(order.getCartId());
                if (cart == null) {
                    // Log and return an appropriate response for a missing cart
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null); // Or return an appropriate error response
                }

                User user = userService.getUserById(cart.getCustomerId());
                if (user == null) {
                    // Log and return an appropriate response for a missing user
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null); // Or return an appropriate error response
                }

                Shop shop = shopService.getShopById(cart.getShopId());
                if (shop == null) {
                    // Log and return an appropriate response for a missing shop
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null); // Or return an appropriate error response
                }

                Mall mall = mallService.getMallById(shop.getMallId());
                if (mall == null) {
                    // Log and return an appropriate response for a missing mall
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null); // Or return an appropriate error response
                }

                List<ItemDetailsDTO> itemDetails = cart.getItemIds().stream()
                    .map(itemId -> {
                        Item item = itemService.getItemById(itemId);
                        return item != null ? new ItemDetailsDTO(item.getItemId(), item.getItemName()) : null;
                    })
                    .filter(itemDTO -> itemDTO != null) // Filter out null ItemDetailsDTOs
                    .collect(Collectors.toList());

                Double totalPrice = itemDetails.stream()
                    .map(itemDTO -> itemService.getItemById(itemDTO.getItemId()))
                    .filter(item -> item != null) // Ensure the item is not null
                    .map(Item::getItemPrice)
                    .reduce(0.0, Double::sum);

                OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    order.getOrderId(),
                    order.getCartId(),
                    cart.getCustomerId(),
                    user.getName(),
                    mall.getMallId(),
                    mall.getMallName(),
                    shop.getShopId(),
                    shop.getShopName(),
                    itemDetails.size(),
                    itemDetails,
                    totalPrice,
                    order.getDateOfPurchase(),
                    order.getPaymentMode(),            
                    user.getId()

                );

                return ResponseEntity.ok(orderDetailsDTO);
            } else {
                // Log and return a response for order not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Integer id, @RequestBody OrderDetails orderDetails) {
        try {
            if (orderDetailsService.getOrderById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
            }
            orderDetailsService.updateOrder(id, orderDetails);
            return ResponseEntity.ok("Order updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error updating order: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        try {
            if (orderDetailsService.getOrderById(id) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
            }
            orderDetailsService.deleteOrder(id);
            return ResponseEntity.ok("Order deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error deleting order: " + e.getMessage());
        }
    }
}
