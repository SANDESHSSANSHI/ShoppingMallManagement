package com.shopping_management.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob; // Import for BLOB

@Entity
public class Shop {

    @Id
    private Integer shopId;
    private String shopName;
    private String shopStatus;
    private Integer shopOwnerId;
    private Integer mallId;

    @Lob // This annotation specifies that this field will be stored as a BLOB in the database
    private byte[] image; // Field to store shop image

    public Shop() {
        super();
    }

    public Shop(Integer shopId, String shopName, String shopStatus, Integer shopOwnerId, Integer mallId, byte[] image) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopStatus = shopStatus;
        this.shopOwnerId = shopOwnerId;
        this.mallId = mallId;
        this.image = image;
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

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public Integer getShopOwnerId() {
        return shopOwnerId;
    }

    public void setShopOwnerId(Integer shopOwnerId) {
        this.shopOwnerId = shopOwnerId;
    }

    public Integer getMallId() {
        return mallId;
    }

    public void setMallId(Integer mallId) {
        this.mallId = mallId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Shop [shopId=" + shopId + ", shopName=" + shopName + ", shopStatus=" + shopStatus 
                + ", shopOwnerId=" + shopOwnerId + ", mallId=" + mallId + "]";
    }
}
