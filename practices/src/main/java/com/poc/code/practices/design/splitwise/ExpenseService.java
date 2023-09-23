package com.poc.code.practices.design.splitwise;

import java.util.List;
import java.util.Map;

public interface ExpenseService {
    void registerEqual(Float amount, User paidBy, List<User> paidFor);

    void registerExact(Float amount, User paidBy, Map<User, Float> paidFor);

    void registerPercent(Float amount, User paidBy, Map<User, Float> paidFor);
}
