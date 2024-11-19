package com.shopping_management.demo.dto;

import java.time.LocalDate;

public class OrderDTO {
	
    private Integer orderId;
    private Integer cartId;  
    private Integer customerId;
    private String name;
    private String mallName;
    private String shopName;
    private Integer noOfItems; 
    private LocalDate dateOfPurchase;
	private Double totalPrice; 
    private String paymentMode;
    
    
    public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

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
	public String getMallName() {
		return mallName;
	}
	public void setMallName(String mallName) {
		this.mallName = mallName;
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
	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", cartId=" + cartId + ", customerId=" + customerId + ", name=" + name
				+ ", mallName=" + mallName + ", shopName=" + shopName + ", noOfItems=" + noOfItems + ", dateOfPurchase="
				+ dateOfPurchase + ", totalPrice=" + totalPrice + ", paymentMode=" + paymentMode + ", getTotalPrice()="
				+ getTotalPrice() + ", getOrderId()=" + getOrderId() + ", getCartId()=" + getCartId()
				+ ", getCustomerId()=" + getCustomerId() + ", getName()=" + getName() + ", getMallName()="
				+ getMallName() + ", getShopName()=" + getShopName() + ", getNoOfItems()=" + getNoOfItems()
				+ ", getDateOfPurchase()=" + getDateOfPurchase() + ", getPaymentMode()=" + getPaymentMode()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public OrderDTO(Integer orderId, Integer cartId, Integer customerId, String name, String mallName, String shopName,
			Integer noOfItems, LocalDate dateOfPurchase, Double totalPrice, String paymentMode) {
		super();
		this.orderId = orderId;
		this.cartId = cartId;
		this.customerId = customerId;
		this.name = name;
		this.mallName = mallName;
		this.shopName = shopName;
		this.noOfItems = noOfItems;
		this.dateOfPurchase = dateOfPurchase;
		this.totalPrice = totalPrice;
		this.paymentMode = paymentMode;
	}
	public OrderDTO() {
		super();
	}
	

    

}
