package com.poc.code.practices.design.Trello;

public interface UserService {
    User create(String name, String email);

    User get(String id);

    void delete(String id);
}
