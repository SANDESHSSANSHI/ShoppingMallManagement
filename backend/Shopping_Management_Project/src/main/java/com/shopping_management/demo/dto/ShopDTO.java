package com.shopping_management.demo.dto;

public class ShopDTO {

    private Integer shopId;  
    private String shopName;
    private Integer shopOwnerId;  
    private String shopownername;
    private String shopStatus;
    private Integer mallId;
    private String mallName;
    private Integer no_of_items;
    private Integer no_of_employees;
    
    
	public Integer getNo_of_items() {
		return no_of_items;
	}
	public void setNo_of_items(Integer no_of_items) {
		this.no_of_items = no_of_items;
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
	public Integer getShopOwnerId() {
		return shopOwnerId;
	}
	public void setShopOwnerId(Integer shopOwnerId) {
		this.shopOwnerId = shopOwnerId;
	}
	public String getShopownername() {
		return shopownername;
	}
	public void setShopownername(String shopownername) {
		this.shopownername = shopownername;
	}
	public String getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
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

	public Integer getNo_of_employees() {
		return no_of_employees;
	}
	public void setNo_of_employees(Integer no_of_employees) {
		this.no_of_employees = no_of_employees;
	}
	public ShopDTO(Integer shopId, String shopName, Integer shopOwnerId, String shopownername, String shopStatus,
			Integer mallId, String mallName, Integer no_of_items, Integer no_of_employees) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopOwnerId = shopOwnerId;
		this.shopownername = shopownername;
		this.shopStatus = shopStatus;
		this.mallId = mallId;
		this.mallName = mallName;
		this.no_of_items = no_of_items;
		this.no_of_employees = no_of_employees;
	}
	public ShopDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    

}
