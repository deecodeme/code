package com.poc.code.design.Trello;

public class UserImpl implements User {
    private String userId;
    private String name;
    private String email;

    public UserImpl(String userId, String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
