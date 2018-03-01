package com.example.dao.entity;

public class CustomerEntity {
    private int id;
    private String name;
    private String password;
    private String phone;
    private String mail;
    private int deptId;
    private int corpId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return mail;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getCorpId() {
        return corpId;
    }
}