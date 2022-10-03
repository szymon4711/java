package database;

import account.Account;

import java.sql.*;
import java.util.ArrayList;

public class Database implements IDatabase {
    public static ArrayList<Account> accounts;

    public Database() {
        accounts = new ArrayList<>();
    }

    @Override
    public void createDatabase() {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3307/bank",
                        "root", "password");
                Statement stmt = conn.createStatement();
        ) {
            String strSelect = "select username, pin, money, investment, accountNumber from person";
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                String username = rset.getString("username");
                String pin = rset.getString("pin");
                double money = rset.getDouble("money");
                double investment = rset.getDouble("investment");
                String accountNumber = rset.getString("accountNumber");
                accounts.add(new Account(username, pin, money, investment, accountNumber));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateDatabase() {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3307/bank",
                        "root", "password");
                Statement stmt = conn.createStatement();
        ) {

            for (Account currentAccount : Database.accounts) {
                String username = currentAccount.getUsername();
                String pin = currentAccount.getPin();
                double money = currentAccount.getMoney();
                double investment = currentAccount.getInvestment();
                String accountNumber = currentAccount.getAccountNumber();
                String strUpdate = "update person set pin = '" + pin + "', money = " + money + ", investment = " +
                        investment + " where accountNumber = '" + accountNumber + "'";
                System.out.println(strUpdate);
                stmt.executeUpdate(strUpdate);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
