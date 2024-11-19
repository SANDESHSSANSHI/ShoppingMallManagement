package com.shopping_management.demo.dto;

public class Shops_for_mallDTO {
    private Integer shopId;
    private String shopName;
    private Integer shopOwnerId;

    // Constructor
   
    public Integer getShopOwnerId() {
		return shopOwnerId;
	}

	public Shops_for_mallDTO() {
		super();
	}

	public Shops_for_mallDTO(Integer shopId, String shopName, Integer shopOwnerId) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopOwnerId = shopOwnerId;
	}

	public void setShopOwnerId(Integer shopOwnerId) {
		this.shopOwnerId = shopOwnerId;
	}

	// Getters and Setters
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
}
