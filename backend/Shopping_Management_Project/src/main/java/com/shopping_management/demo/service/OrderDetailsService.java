package com.shopping_management.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping_management.demo.model.OrderDetails;
import com.shopping_management.demo.repository.OrderDetailsRepository;

@Service
@Transactional
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository repository;

    public List<OrderDetails> getAllOrders() {
        return repository.findAll();
    }

    public OrderDetails getOrderById(Integer orderId) {
        return repository.findById(orderId).orElse(null);
    }

    public List<OrderDetails> getOrdersByCustomerId(Integer customerId) {
        return repository.findByCustomerId(customerId); // Ensure this method exists
    }



    public void createOrder(OrderDetails orderDetails) {
        if (orderDetails == null) {
            throw new IllegalArgumentException("Order details cannot be null");
        }
        repository.save(orderDetails);
    }

    public void updateOrder(Integer orderId, OrderDetails orderDetails) {
        if (orderDetails == null) {
            throw new IllegalArgumentException("Order details cannot be null");
        }
        orderDetails.setOrderId(orderId);
        repository.save(orderDetails);
    }

    public void deleteOrder(Integer orderId) {
        repository.deleteById(orderId);
    }
}
