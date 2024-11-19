package com.shopping_management.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob; // Import for BLOB
import java.time.LocalDate;

@Entity
public class Item {

    @Id
    private Integer itemId;
    private String itemName;
    private String itemManufacture;
    private LocalDate itemExpiryDate;
    private Double itemPrice;
    private String itemCategory;
    private Integer shopId;

    @Lob // This annotation specifies that this field will be stored as a BLOB in the database
    private byte[] image; // Field to store image

    // Constructor, getters, and setters
    public Item() {
        super();
    }

    public Item(Integer itemId, String itemName, String itemManufacture, LocalDate itemExpiryDate, 
                Double itemPrice, String itemCategory, Integer shopId, byte[] image) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemManufacture = itemManufacture;
        this.itemExpiryDate = itemExpiryDate;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.shopId = shopId;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemManufacture=" + itemManufacture 
                + ", itemExpiryDate=" + itemExpiryDate + ", itemPrice=" + itemPrice 
                + ", itemCategory=" + itemCategory + ", shopId=" + shopId + "]";
    }
}
