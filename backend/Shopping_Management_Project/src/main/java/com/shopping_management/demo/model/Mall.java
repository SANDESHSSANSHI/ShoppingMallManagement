package com.shopping_management.demo.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
public class Mall {

    @Id
    private Integer mallId;  
    private String mallName;
    private Integer mallAdminId; 
    private String mallLocation;
    private String mallCategory;

    public Mall() {
        super();  
    }

    public Mall(Integer mallId, String mallName, Integer mallAdminId, String mallLocation, String mallCategory) {
        super(); 
        this.mallId = mallId;
        this.mallName = mallName;
        this.mallAdminId = mallAdminId;
        this.mallLocation = mallLocation;
        this.mallCategory = mallCategory;
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

    @Override
    public String toString() {
        return "Mall [mallId=" + mallId + ", mallName=" + mallName + ", mallAdminId=" + mallAdminId 
                + ", mallLocation=" + mallLocation + ", mallCategory=" + mallCategory + "]";
    }
}
