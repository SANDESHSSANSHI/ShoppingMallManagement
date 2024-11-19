package com.shopping_management.demo.dto;

import java.util.List;

import com.shopping_management.demo.model.Employee;
import com.shopping_management.demo.model.Item;


public class ShopEmpDTO {
    private Integer shopId;
    private String shopName;
    private Integer shopOwnerId;
    private String shopOwnerName;
    private String shopStatus;
    private Integer mallId;
    private String mallName;
    private Integer noOfEmployees;
    private List<Employee> employees;
    private List<Item> items;  // New field for items

    // Constructor
    public ShopEmpDTO(Integer shopId, String shopName, Integer shopOwnerId, String shopOwnerName, String shopStatus,
                      Integer mallId, String mallName, Integer noOfEmployees, List<Employee> employees, List<Item> items) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopOwnerId = shopOwnerId;
        this.shopOwnerName = shopOwnerName;
        this.shopStatus = shopStatus;
        this.mallId = mallId;
        this.mallName = mallName;
        this.noOfEmployees = noOfEmployees;
        this.employees = employees;
        this.items = items;
    }

    // Getters and Setters
    // existing getters and setters...
    
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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

	public String getShopOwnerName() {
		return shopOwnerName;
	}

	public void setShopOwnerName(String shopOwnerName) {
		this.shopOwnerName = shopOwnerName;
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

	public Integer getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(Integer noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
    
    
}
