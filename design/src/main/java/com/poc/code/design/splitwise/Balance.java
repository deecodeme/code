package com.poc.code.design.splitwise;

import java.util.Objects;

public class Balance {
    public User getUserOwed() {
        return userOwed;
    }

    public User getUser() {
        return user;
    }

    private User userOwed;
    private User user;
    private Float amount;

    @Override
    public String toString() {
        return "Balance{" +
                "userOwed=" + userOwed +
                ", user=" + user +
                ", amount=" + amount +
                '}';
    }

    public Balance(User userOwed, User user, Float amount) {
        this.userOwed = userOwed;
        this.user = user;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(userOwed, balance.userOwed) && Objects.equals(user, balance.user) && Objects.equals(amount, balance.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userOwed, user, amount);
    }
}
