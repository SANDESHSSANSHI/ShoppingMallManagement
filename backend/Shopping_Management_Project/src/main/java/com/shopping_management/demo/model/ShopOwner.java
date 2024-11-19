package com.shopping_management.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ShopOwner {

    @Id
    private Integer shopOwnerId;
    private Integer shopId;

    public ShopOwner() {
        super();
    }

    public ShopOwner(Integer shopOwnerId, Integer shopId) {
        super();
        this.shopOwnerId = shopOwnerId;
        this.shopId = shopId;
    }

    public Integer getShopOwnerId() {
        return shopOwnerId;
    }

    public void setShopOwnerId(Integer shopOwnerId) {
        this.shopOwnerId = shopOwnerId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "ShopOwner [shopOwnerId=" + shopOwnerId + ", shopId=" + shopId + "]";
    }
}
