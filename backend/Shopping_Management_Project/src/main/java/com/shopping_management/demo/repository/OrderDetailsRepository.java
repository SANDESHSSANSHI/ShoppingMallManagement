package com.shopping_management.demo.repository;

import com.shopping_management.demo.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByCustomerId(Integer customerId);
//    OrderDetails findByRazorpayOrderId(String razorpayOrderId);
}
