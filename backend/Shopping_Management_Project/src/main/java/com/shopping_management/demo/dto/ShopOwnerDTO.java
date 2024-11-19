
package com.shopping_management.demo.dto;

public class ShopOwnerDTO {

    private Integer shopownerId;
    private String shopownername;
	private Integer shopId;
    private String shopname;
    private String password;
    private String mobileNumber;
    private String mail;
	public Integer getShopownerId() {
		return shopownerId;
	}
	public void setShopownerId(Integer shopownerId) {
		this.shopownerId = shopownerId;
	}
	public String getShopownername() {
		return shopownername;
	}
	public void setShopownername(String shopownername) {
		this.shopownername = shopownername;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
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
	public ShopOwnerDTO(Integer shopownerId, String shopownername, Integer shopId, String shopname, String password,
			String mobileNumber, String mail) {
		super();
		this.shopownerId = shopownerId;
		this.shopownername = shopownername;
		this.shopId = shopId;
		this.shopname = shopname;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.mail = mail;
	}
	public ShopOwnerDTO() {
		super();
	}
    
    
	
	
}
