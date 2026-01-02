package entities;


import org.junit.jupiter.api.Test;

import factory.AccountFactory;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTests {


    @Test
    public void depositShouldIncreaseBalanceWhenPositiveAmount() {
        double amount = 200.0;
        double expectedValue = 196.0;
        Account acc = AccountFactory.createEmptyAccount();
        acc.deposit(200.0);
        assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void depositShouldDoNothingWhenNegativeAmount() {
        double expectedValue = 100.0;
        Account acc = AccountFactory.createAccount(expectedValue);
        double amount = -200.00;
        acc.deposit(amount);
        assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void fullWithdrawShouldClearBalance (){
        double expectedValue = 0.0;
        double initialBalance = 800;
        Account acc = AccountFactory.createAccount(800);
        double result = acc.fullWithdraw();
        assertTrue(expectedValue == acc.getBalance());
        assertTrue(result == initialBalance);
    }

    @Test
    public void withdrawShouldDecreaseWhenSufficientBalance(){
        Account acc = AccountFactory.createAccount(800.00);
        acc.withdraw(500.00);
        assertEquals(300.00,acc.getBalance());
    }

    @Test
    public void withdrawShouldThrowExceptionWhenInsufficientBalance (){
        assertThrows(IllegalArgumentException.class,() -> {
            Account acc = AccountFactory.createAccount(800.00);
            acc.withdraw(801.00);

        });

    }




}
