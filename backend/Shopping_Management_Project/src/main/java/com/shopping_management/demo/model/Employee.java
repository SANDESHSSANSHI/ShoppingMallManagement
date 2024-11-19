package com.shopping_management.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    private Integer employeeId;  
    private String employeeName;
    private LocalDate employeeDateOfBirth;  
    private Double employeeSalary;
    private String employeeAddress;
    private String employeeDesignation;
    private Integer shopId;  

    public Employee() {
        super();  
    }

    public Employee(Integer employeeId, String employeeName, LocalDate employeeDateOfBirth, 
                    Double employeeSalary, String employeeAddress, 
                    String employeeDesignation, Integer shopId) {
        super();  
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeDateOfBirth = employeeDateOfBirth;
        this.employeeSalary = employeeSalary;
        this.employeeAddress = employeeAddress;
        this.employeeDesignation = employeeDesignation;
        this.shopId = shopId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public void setEmployeeDateOfBirth(LocalDate employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    public Double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName 
                + ", employeeDateOfBirth=" + employeeDateOfBirth + ", employeeSalary=" + employeeSalary 
                + ", employeeAddress=" + employeeAddress + ", employeeDesignation=" + employeeDesignation 
                + ", shopId=" + shopId + "]";
    }
}
