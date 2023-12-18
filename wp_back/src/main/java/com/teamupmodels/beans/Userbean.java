package com.teamupmodels.beans;

public class Userbean {
    private String id;
    private String password;
    private String name;
    private String phone;
    
    
    // 기본 생성자
    public Userbean() {}

    public Userbean(String id, String pw, String name, String phone) {
		this.id = id;
		this.password = pw;
		this.name = name;
		this.phone = phone;
	}

	// Getter와 Setter 메소드
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
