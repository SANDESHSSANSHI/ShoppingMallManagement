package com.shopping_management.demo.dto;

import java.time.LocalDate;

public class EmployeeDTO {

    private Integer employeeId;
    private String employeeName;
    private LocalDate employeeDateOfBirth;
    private Double employeeSalary;
    private String employeeAddress;
    private String employeeDesignation;
    private Integer shopId;
    private String shopName;
    private Integer mallId;
    private String mallName;

    public EmployeeDTO() {
        super();
    }

    public EmployeeDTO(Integer employeeId, String employeeName, LocalDate employeeDateOfBirth,
                       Double employeeSalary, String employeeAddress, String employeeDesignation, 
                       Integer shopId, String shopName, Integer mallId, String mallName) {
        super();
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeDateOfBirth = employeeDateOfBirth;
        this.employeeSalary = employeeSalary;
        this.employeeAddress = employeeAddress;
        this.employeeDesignation = employeeDesignation;
        this.shopId = shopId;
        this.shopName = shopName;
        this.mallId = mallId;
        this.mallName = mallName;
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

    @Override
    public String toString() {
        return "EmployeeDTO [employeeId=" + employeeId + ", employeeName=" + employeeName 
                + ", employeeDateOfBirth=" + employeeDateOfBirth + ", employeeSalary=" + employeeSalary 
                + ", employeeAddress=" + employeeAddress + ", employeeDesignation=" + employeeDesignation 
                + ", shopId=" + shopId + ", shopName=" + shopName + ", mallId=" + mallId 
                + ", mallName=" + mallName + "]";
    }
}
