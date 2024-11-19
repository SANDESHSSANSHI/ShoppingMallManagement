package com.shopping_management.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MallAdmin {

    @Id
    private Integer mallAdminId;  
    private Integer mallId;       

    public MallAdmin() {
        super();  
    }

    public MallAdmin(Integer mallAdminId, Integer mallId) {
        super();  
        this.mallAdminId = mallAdminId;
        this.mallId = mallId;
    }

    // Getters and Setters
    public Integer getMallAdminId() {
        return mallAdminId;
    }

    public void setMallAdminId(Integer mallAdminId) {
        this.mallAdminId = mallAdminId;
    }

    public Integer getMallId() {
        return mallId;
    }

    public void setMallId(Integer mallId) {
        this.mallId = mallId;
    }

    @Override
    public String toString() {
        return "MallAdmin [mallAdminId=" + mallAdminId + ", mallId=" + mallId + "]";
    }
}
