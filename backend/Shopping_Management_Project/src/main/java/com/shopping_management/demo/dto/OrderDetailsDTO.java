package com.shopping_management.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDetailsDTO {

    private Integer orderId;
    private Integer cartId;  
    private Integer customerId;
    private String name;
    private Integer mallId;  
    private String mallName;
    private Integer shopId;
    private String shopName;
    private Integer noOfItems; 
    private List<ItemDetailsDTO> itemDetails;  
    private Double totalPrice; 
    private LocalDate dateOfPurchase; 
    private String paymentMode;
    private Integer userId; // Changed id to userId for clarity

    // Constructor with all fields
    public OrderDetailsDTO(Integer orderId, Integer cartId, Integer customerId, String name, Integer mallId,
            String mallName, Integer shopId, String shopName, Integer noOfItems, List<ItemDetailsDTO> itemDetails,
            Double totalPrice, LocalDate dateOfPurchase, String paymentMode, Integer userId) {
        super();
        this.orderId = orderId;
        this.cartId = cartId;
        this.customerId = customerId;
        this.name = name;
        this.mallId = mallId;
        this.mallName = mallName;
        this.shopId = shopId;
        this.shopName = shopName;
        this.noOfItems = noOfItems;
        this.itemDetails = itemDetails;
        this.totalPrice = totalPrice;
        this.dateOfPurchase = dateOfPurchase;
        this.paymentMode = paymentMode;
        this.userId = userId; // Updated to userId
    }

    // Default constructor
    public OrderDetailsDTO() {
        super();
    }

    // Getters and Setters
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMallId() {
        return mallId;
    }

    public void setMallId(Integer mallId) {
        this.mallId = mallId;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(Integer noOfItems) {
        this.noOfItems = noOfItems;
    }

    public List<ItemDetailsDTO> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetailsDTO> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public Integer getUserId() { // Updated getter
        return userId;
    }

    public void setUserId(Integer userId) { // Updated setter
        this.userId = userId;
    }
}
