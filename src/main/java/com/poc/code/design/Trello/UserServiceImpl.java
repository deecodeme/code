package com.poc.code.design.Trello;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class UserServiceImpl implements UserService {
    private AtomicLong idCounter;
    private Map<String, User> users;

    public UserServiceImpl() {
        idCounter = new AtomicLong();
    }

    @Override
    public User create(String name, String email) {
        String userId = "user" + idCounter.incrementAndGet();
        return new UserImpl(userId, name, email);
    }

    @Override
    public User get(String id) throws RuntimeException {
        if (users.containsKey(id)) {
            return users.get(id);
        } else {
            throw new RuntimeException(String.format("User with id: %s not found", id));
        }
    }

    @Override
    public void delete(String id) throws RuntimeException {
        if (users.containsKey(id)) {
            users.remove(id);
        } else {
            throw new RuntimeException(String.format("User with id: %s not found", id));
        }
    }
}
