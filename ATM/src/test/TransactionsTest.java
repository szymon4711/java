package test;

import account.Account;
import database.Database;
import transactions.Transactions;

import static org.junit.jupiter.api.Assertions.*;

class TransactionsTest {

    @org.junit.jupiter.api.Test
    void wyplata() {
        Account account = new Account("User", "1234", 1000, 1000, "123456");
        assertTrue(Transactions.wyplata(account, 1000));
        assertFalse(Transactions.wyplata(account, 1001));
    }

    @org.junit.jupiter.api.Test
    void przelewWewnetrzny() {
        Account account = new Account("User_1", "1234", 1000, 1000, "123456");
        assertTrue(Transactions.przelewWewnetrzny(account, 1000, 0));
        assertFalse(Transactions.przelewWewnetrzny(account, 1001, 0));
        assertTrue(Transactions.przelewWewnetrzny(account, 1000, 1));
        assertFalse(Transactions.przelewWewnetrzny(account, 10001, 1));
    }

    @org.junit.jupiter.api.Test
    void przelew() {
        Account account_1 = new Account("User_1", "1234", 1000, 1000, "123456");
        Account account_2 = new Account("User_2", "1234", 1000, 1000, "654321");
        Database database = new Database();
        Database.accounts.add(account_1);
        Database.accounts.add(account_2);
        assertTrue(Transactions.przelew(account_1, "654321", 1000));
        assertFalse(Transactions.przelew(account_1, "654321", 1001));
    }

    @org.junit.jupiter.api.Test
    void database(){
        Database database = new Database();
        database.createDatabase();
        assertEquals(Database.accounts.get(0).getUsername(), "szymon");
    }
}