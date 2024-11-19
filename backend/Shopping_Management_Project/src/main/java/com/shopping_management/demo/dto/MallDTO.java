package com.shopping_management.demo.dto;

public class MallDTO {

    private Integer mallId; 
    private String mallName;
    private Integer mallAdminId;  
    private String mallLocation;
    private String mallCategory;
    private Integer no_of_shops;
    
    
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
	public Integer getMallAdminId() {
		return mallAdminId;
	}
	public void setMallAdminId(Integer mallAdminId) {
		this.mallAdminId = mallAdminId;
	}
	public String getMallLocation() {
		return mallLocation;
	}
	public void setMallLocation(String mallLocation) {
		this.mallLocation = mallLocation;
	}
	public String getMallCategory() {
		return mallCategory;
	}
	public void setMallCategory(String mallCategory) {
		this.mallCategory = mallCategory;
	}
	public Integer getNo_of_shops() {
		return no_of_shops;
	}
	public void setNo_of_shops(Integer no_of_shops) {
		this.no_of_shops = no_of_shops;
	}
	public MallDTO(Integer mallId, String mallName, Integer mallAdminId, String mallLocation, String mallCategory,
			Integer no_of_shops) {
		super();
		this.mallId = mallId;
		this.mallName = mallName;
		this.mallAdminId = mallAdminId;
		this.mallLocation = mallLocation;
		this.mallCategory = mallCategory;
		this.no_of_shops = no_of_shops;
	}
	public MallDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    
}
