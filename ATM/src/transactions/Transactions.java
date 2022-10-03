package transactions;

import account.Account;
import database.Database;
import view.Bankomat;

import javax.xml.crypto.Data;

public class Transactions {
    public static void wplata(Account account, double money) {
        account.setMoney(account.getMoney() + money);
    }

    public static boolean wyplata(Account account, double money) {
        if (account.getMoney() >= money) {
            account.setMoney(account.getMoney() - money);
            return true;
        } else
            return false;
    }

    public static boolean przelewWewnetrzny(Account account, double money, int choice) {
        if (choice == 0) { // glowne -> lokata
            if (account.getMoney() >= money) {
                account.setMoney(account.getMoney() - money);
                account.setInvestment(account.getInvestment() + money);
                return true;
            } else
                return false;

        } else if (choice == 1) { // lokata -> glowne
            if (account.getInvestment() >= money) {
                account.setInvestment(account.getInvestment() - money);
                account.setMoney(account.getMoney() + money);
                return true;
            } else
                return false;
        } else return false;
    }

    public static boolean przelew(Account account, String accNr, double money) {
        int index;
        //Account account1;
        boolean statement;
        for (Account currentAccount : Database.accounts) {
            if (currentAccount.getAccountNumber().equals(accNr)) {
                statement = wyplata(account, money);
                if (statement) {
                    wplata(currentAccount, money);
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }


//    public static boolean przelew(Account account, String accNr, double money){
//        wyplata(account, money);
//        return true;
//    }
}
