package com.poc.code.practices.design.splitwise;

public class UserImpl implements User {
    private Integer id;
    private String name;
    private String mobile;
    private String email;

    public UserImpl(Integer id, String name, String mobile, String email) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Integer getUserId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getMobileNumber() {
        return this.mobile;
    }

    @Override
    public String getEmail() {
        return this.email;
    }
}
