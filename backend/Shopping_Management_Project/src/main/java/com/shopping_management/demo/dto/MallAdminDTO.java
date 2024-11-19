package com.shopping_management.demo.dto;

public class MallAdminDTO {

    private Integer mallAdminId;  
    private String malladminname;
    private Integer mallId;
    private String mallName;
    private String password;
    private String mobileNumber;
    private String mail;
	public Integer getMallAdminId() {
		return mallAdminId;
	}
	public void setMallAdminId(Integer mallAdminId) {
		this.mallAdminId = mallAdminId;
	}
	public String getMalladminname() {
		return malladminname;
	}
	public void setMalladminname(String malladminname) {
		this.malladminname = malladminname;
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
	public MallAdminDTO(Integer mallAdminId, String malladminname, Integer mallId, String mallName, String password,
			String mobileNumber, String mail) {
		super();
		this.mallAdminId = mallAdminId;
		this.malladminname = malladminname;
		this.mallId = mallId;
		this.mallName = mallName;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.mail = mail;
	}
	public MallAdminDTO() {
		super();
	}
    
    
    
    
}
