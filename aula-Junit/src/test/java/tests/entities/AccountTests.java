package tests.entities;


import entities.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTests {


    @Test
    public void depositShouldIncreaseBalanceWhenPositiveAmount() {
        double amount = 200.0;
        double expectedValue = 196.0;
        Account acc = new Account(1L, 0.0);
        acc.deposit(200.0);
        assertEquals(expectedValue, acc.getBalance());
    }

    
}
