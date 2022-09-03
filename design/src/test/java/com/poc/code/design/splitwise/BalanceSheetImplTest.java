package com.poc.code.design.splitwise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BalanceSheetImplTest {
    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void getBalance() {
        User user1 = new UserImpl(1, "user1", "user1Mobile", "user1Email");
        User user2 = new UserImpl(2, "user2", "user2Mobile", "user2Email");
        User user3 = new UserImpl(3, "user3", "user3Mobile", "user3Email");
        User user4 = new UserImpl(4, "user4", "user4Mobile", "user4Email");
        BalanceSheet balanceSheet = new BalanceSheetImpl();
        balanceSheet.createBalanceSheet(user1);
        balanceSheet.createBalanceSheet(user2);
        balanceSheet.createBalanceSheet(user3);
        balanceSheet.createBalanceSheet(user4);
        Assertions.assertTrue(balanceSheet.getBalance(user1).size() == 0);
        Assertions.assertTrue(balanceSheet.getBalance(user2).size() == 0);
        Assertions.assertTrue(balanceSheet.getBalance(user3).size() == 0);
        Assertions.assertTrue(balanceSheet.getBalance(user4).size() == 0);

        balanceSheet.addBalance(user1, user2, 100f);
        Assertions.assertEquals(balanceSheet.getBalance(user1).size(), 0);
        Assertions.assertTrue(balanceSheet.getBalance(user2).size() == 1 &&
                balanceSheet.getBalance(user2).get(0).equals(new Balance(user2, user1, 100f)));

        balanceSheet.addBalance(user2, user1, 50f);
        Assertions.assertEquals(balanceSheet.getBalance(user1).size(), 0);
        Assertions.assertEquals(balanceSheet.getBalance(user2).size(), 1);
        Assertions.assertTrue(balanceSheet.getBalance(user2).get(0).equals(new Balance(user2, user1, 50f)));

        balanceSheet.addBalance(user2, user1, 100f);
        Assertions.assertTrue(balanceSheet.getBalance(user1).size() == 1 &&
                balanceSheet.getBalance(user1).get(0).equals(new Balance(user1, user2, 50f)));
        Assertions.assertEquals(balanceSheet.getBalance(user2).size(), 0);
    }

    @Test
    public void testGetBalance() {
        User user1 = new UserImpl(1, "user1", "user1Mobile", "user1Email");
        User user2 = new UserImpl(2, "user2", "user2Mobile", "user2Email");
        User user3 = new UserImpl(3, "user3", "user3Mobile", "user3Email");
        User user4 = new UserImpl(4, "user4", "user4Mobile", "user4Email");
        BalanceSheet balanceSheet = new BalanceSheetImpl();
        balanceSheet.createBalanceSheet(user1);
        balanceSheet.createBalanceSheet(user2);
        balanceSheet.createBalanceSheet(user3);
        balanceSheet.createBalanceSheet(user4);
        Assertions.assertTrue(balanceSheet.getBalance(user1).size() == 0);
        Assertions.assertTrue(balanceSheet.getBalance(user2).size() == 0);
        Assertions.assertTrue(balanceSheet.getBalance(user3).size() == 0);
        Assertions.assertTrue(balanceSheet.getBalance(user4).size() == 0);

        balanceSheet.addBalance(user1, user2, 100f);
        Assertions.assertEquals(balanceSheet.getBalance(user1).size(), 0);
        Assertions.assertTrue(balanceSheet.getBalance(user2).size() == 1 &&
                balanceSheet.getBalance(user2).get(0).equals(new Balance(user2, user1, 100f)));

        balanceSheet.addBalance(user2, user1, 50f);
        Assertions.assertEquals(balanceSheet.getBalance(user1).size(), 0);
        Assertions.assertEquals(balanceSheet.getBalance(user2).size(), 1);
        Assertions.assertTrue(balanceSheet.getBalance(user2).get(0).equals(new Balance(user2, user1, 50f)));

        balanceSheet.addBalance(user2, user1, 100f);
        Assertions.assertTrue(balanceSheet.getBalance(user1).size() == 1 &&
                balanceSheet.getBalance(user1).get(0).equals(new Balance(user1, user2, 50f)));
        Assertions.assertEquals(balanceSheet.getBalance(user2).size(), 0);

        balanceSheet.addBalance(user3, user4, 150f);
        balanceSheet.addBalance(user1, user4, 150f);
        balanceSheet.addBalance(user2, user4, 150f);

        Assertions.assertEquals(balanceSheet.getBalance().size(), 4);
        Balance user1ExpectedBalance = new Balance(user1, user2, 50f);
        List<Balance> user4ExpectedBalance = new ArrayList<>();
        user4ExpectedBalance.add(new Balance(user4, user1, 150f));
        user4ExpectedBalance.add(new Balance(user4, user2, 150f));
        user4ExpectedBalance.add(new Balance(user4, user3, 150f));
        balanceSheet.getBalance().stream().forEach(v -> {
            if (v.getUser() == user1) {
                Assertions.assertTrue(v.equals(user1ExpectedBalance));
            }
        });
    }
}