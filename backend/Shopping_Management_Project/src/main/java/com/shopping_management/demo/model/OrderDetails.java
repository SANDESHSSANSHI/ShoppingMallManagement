package com.shopping_management.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class OrderDetails {

    @Id
    private Integer orderId;
    private LocalDate dateOfPurchase;
    private String paymentMode;
    private Integer cartId;
    private Integer customerId;
    

    public OrderDetails() {
        super();
    }

    public OrderDetails(Integer orderId, LocalDate dateOfPurchase, String paymentMode, Integer cartId, Integer customerId) {
        super();
        this.orderId = orderId;
        this.dateOfPurchase = dateOfPurchase;
        this.paymentMode = paymentMode;
        this.cartId = cartId;
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getcustomerId() {
        return customerId;
    }

    public void setcustomerId(Integer id) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "OrderDetails [orderId=" + orderId + ", dateOfPurchase=" + dateOfPurchase 
                + ", paymentMode=" + paymentMode + ", cartId=" + cartId + ", customerId=" + customerId + "]";
    }
}
