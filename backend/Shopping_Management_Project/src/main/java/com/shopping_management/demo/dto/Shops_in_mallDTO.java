
package com.shopping_management.demo.dto;
import java.util.List;

import com.shopping_management.demo.model.Shop;
public class Shops_in_mallDTO {
    private Integer mallId;
    private String mallName;
    private Integer mallAdminId;
    private String mallLocation;
    private String mallCategory;
    private Integer noOfShops;
    private List<Shop> shops;

    // Constructor
    public Shops_in_mallDTO(Integer mallId, String mallName, Integer mallAdminId, String mallLocation, String mallCategory, Integer noOfShops, List<Shop> shops) {
        this.mallId = mallId;
        this.mallName = mallName;
        this.mallAdminId = mallAdminId;
        this.mallLocation = mallLocation;
        this.mallCategory = mallCategory;
        this.noOfShops = noOfShops;
        this.shops = shops;
    }

    // Getters and Setters
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

    public Integer getNoOfShops() {
        return noOfShops;
    }

    public void setNoOfShops(Integer noOfShops) {
        this.noOfShops = noOfShops;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
