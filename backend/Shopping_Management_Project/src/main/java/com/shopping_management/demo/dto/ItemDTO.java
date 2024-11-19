package com.shopping_management.demo.dto;

import java.time.LocalDate;

public class ItemDTO {

    private Integer itemId;
    private String itemName;
    private String itemManufacture;
    private LocalDate itemExpiryDate;
    private Double itemPrice;
    private String itemCategory;
    private Integer shopId;
    private String shopName;
    private Integer mallId;
    private String mallName;
    private byte[] image; // Add image field

    public ItemDTO(Integer itemId, String itemName, String itemManufacture, LocalDate itemExpiryDate, Double itemPrice,
                   String itemCategory, Integer shopId, String shopName, Integer mallId, String mallName, byte[] image) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemManufacture = itemManufacture;
        this.itemExpiryDate = itemExpiryDate;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.shopId = shopId;
        this.shopName = shopName;
        this.mallId = mallId;
        this.mallName = mallName;
        this.image = image; // Initialize the image field
    }

    // Getters and Setters
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemManufacture() {
        return itemManufacture;
    }

    public void setItemManufacture(String itemManufacture) {
        this.itemManufacture = itemManufacture;
    }

    public LocalDate getItemExpiryDate() {
        return itemExpiryDate;
    }

    public void setItemExpiryDate(LocalDate itemExpiryDate) {
        this.itemExpiryDate = itemExpiryDate;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ItemDTO() {
        super();
    }
}




