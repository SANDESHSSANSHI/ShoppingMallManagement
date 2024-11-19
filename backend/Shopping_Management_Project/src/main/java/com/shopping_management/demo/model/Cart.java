package com.shopping_management.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    private Integer cartid;  
    private Integer customerId;
    private Integer shopId;

    @ElementCollection
    @CollectionTable(name = "cart_item_ids", joinColumns = @JoinColumn(name = "cart_id"))
    @Column(name = "item_id")
    private List<Integer> itemIds;

    // Constructors
    public Cart(Integer cartid, Integer customerId, Integer shopId, List<Integer> itemIds) {
        this.cartid = cartid;
        this.customerId = customerId;
        this.shopId = shopId;
        this.itemIds = itemIds;
    }

    public Cart() {
    }

    // Getters and Setters
    public Integer getCartid() {
        return cartid;
    }

    public void setCartid(Integer cartid) {
        this.cartid = cartid;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public List<Integer> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Integer> itemIds) {
        this.itemIds = itemIds;
    }
    
    
    public int getItemCount(List<Integer> itemIds) {
        return itemIds != null ? itemIds.size() : 0;
    }

    @Override
    public String toString() {
        return "Cart [cartid=" + cartid + ", customerId=" + customerId + ", shopId=" + shopId + ", itemIds=" + itemIds + "]";
    }
}
