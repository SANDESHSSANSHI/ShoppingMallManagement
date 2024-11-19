package com.shopping_management.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
    private String name;
    private String type;  
    private String password;
    private String mobileNumber;
    private String mail;
    

    
    public User() {
		super();
	}

	public User(Integer id, String name, String type, String password, String mobileNumber, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.mail = mail;
	}

	// Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name 
               + ", type=" + type + ", password=" + password 
               + ", mobileNumber=" + mobileNumber + ", mail=" + mail + "]";
    }
}
