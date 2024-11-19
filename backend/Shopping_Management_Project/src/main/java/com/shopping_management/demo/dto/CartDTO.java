package com.shopping_management.demo.dto;

public class CartDTO {

	
    private Integer cartid; 
    private Integer customerId;
    private String name;
    private Integer shopId;
    private String shopName;
    private Integer no_of_items;
    private Double totalprice;
    
    

	public CartDTO(Integer cartid, Integer customerId, String name, Integer shopId, String shopName, Integer no_ofitems,
			Double totalprice) {
		super();
		this.cartid = cartid;
		this.customerId = customerId;
		this.name = name;
		this.shopId = shopId;
		this.shopName = shopName;
		this.no_of_items = no_ofitems;
		this.totalprice = totalprice;
	}
	public Integer getCartid() {
		return cartid;
	}
	public void setCartid(Integer cartid) {
		this.cartid = cartid;
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
	public Integer getNo_ofitems() {
		return no_of_items;
	}
	public void setNo_ofitems(Integer no_ofitems) {
		this.no_of_items = no_ofitems;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
   
    
}
