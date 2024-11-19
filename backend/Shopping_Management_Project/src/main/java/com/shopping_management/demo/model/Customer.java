package com.shopping_management.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    private Integer customerId;  
    private Integer orderId;
    
    
    public Customer() {
		super();
	}

	public Customer(Integer customerId, Integer orderId) {
		super();
		this.customerId = customerId;
		this.orderId = orderId;
	}


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", orderId=" + orderId + "]";
    }
}
