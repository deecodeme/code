package com.poc.code.practices.design.splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceSheetImpl implements BalanceSheet {
    private Map<User, Map<User, Float>> sheet;

    public BalanceSheetImpl() {
        sheet = new HashMap<>();
    }

    @Override
    public void addBalance(User userPaid, User userOwed, Float amount) {
        Map<User, Float> userPaidSheet = this.sheet.get(userPaid);
        Map<User, Float> userOwedSheet = this.sheet.get(userOwed);

        if (!userPaidSheet.containsKey(userOwed)) {
            userPaidSheet.put(userOwed, -1 * amount);
            userOwedSheet.put(userPaid, amount);
        } else {
            Float balanceAmount = userPaidSheet.get(userOwed) - amount;
            userPaidSheet.put(userOwed, balanceAmount);
            userOwedSheet.put(userPaid, -1 * balanceAmount);
        }
    }

    @Override
    public void createBalanceSheet(User user) {
        if (!this.sheet.containsKey(user)) {
            this.sheet.put(user, new HashMap<>());
        }
    }

    @Override
    public List<Balance> getBalance() {
        Map<User, Boolean> visited = new HashMap<>();
        List<Balance> output = new ArrayList<>();
        this.sheet.forEach((k, v) -> {
            if (!visited.containsKey(k.getName())) {
                List<Balance> balances = getBalance(k);
                if (balances != null && balances.size() != 0) {
                    output.addAll(balances);
                }
                visited.put(k, true);
            }
        });
        return output;
    }

    @Override
    public List<Balance> getBalance(User user) {
        List<Balance> output = new ArrayList<>();
        this.sheet.get(user).forEach((k, v) -> {
            if (v > 0) {
                output.add(new Balance(user, k, v));
            }
        });
        return output;
    }
}
