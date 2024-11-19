package com.shopping_management.demo.dto;

public class CustomerDTO {

    private Integer customerId; 
    private String customername;
    private String password;
    private String mobileNumber;
    private String mail; 
    private Integer orderId;
    private Integer cartid;  
    private Integer mallId;  
    private String mallName;
    private Integer shopId;
    private String shopName;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getCartid() {
		return cartid;
	}
	public void setCartid(Integer cartid) {
		this.cartid = cartid;
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
	public CustomerDTO(Integer customerId, String customername, String password, String mobileNumber, String mail,
			Integer orderId, Integer cartid, Integer mallId, String mallName, Integer shopId, String shopName) {
		super();
		this.customerId = customerId;
		this.customername = customername;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.mail = mail;
		this.orderId = orderId;
		this.cartid = cartid;
		this.mallId = mallId;
		this.mallName = mallName;
		this.shopId = shopId;
		this.shopName = shopName;
	}
	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    

    
}
